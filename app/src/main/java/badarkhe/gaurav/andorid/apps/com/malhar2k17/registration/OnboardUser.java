package badarkhe.gaurav.andorid.apps.com.malhar2k17.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Save;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.Home;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 21-10-2016.
 */
public class OnboardUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextWatcher, View.OnClickListener {

    private static final String TAG = OnboardUser.class.getName();

    @BindView(R.id.scrollView_main)
    ScrollView scrollView;
    @BindView(R.id.linear_container_spinner)
    ViewGroup spinner_container;

    @BindView(R.id.spinner_college)
    Spinner spinner_college;
    @BindView(R.id.spinner_branch)
    Spinner spinner_brances;
    @BindView(R.id.spinner_section)
    Spinner spinner_section;
    @BindView(R.id.spinner_semister)
    Spinner spinner_semister;

    @BindView(R.id.input_name_first)
    EditText edt_name_first;
    @BindView(R.id.input_name_last)
    EditText edt_name_last;
    @BindView(R.id.input_phone)
    EditText edt_phone;

    @BindView(R.id.sign_up)
    Button sign_up;

    String val_college = null;
    String val_branch = null;
    String val_section = null;
    String val_sem = null;

    String val_name_first = null;
    String val_name_last = null;
    long val_phone = 0;

    boolean visible;

    Handler scroll = new Handler();
    SweetAlertDialog sweetAlertDialog;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (User.isLoggedIn(this)) {
            Utils.launch(this, Home.class, true);
        }

        setContentView(R.layout.base_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher
//        );


        initiate();
    }

    private void initiate() {

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Saving..");
        sweetAlertDialog.setContentText("Please wait");
        sweetAlertDialog.setCancelable(false);


        setupSpinner(spinner_brances, Constants.Arrays.branches);
        setupSpinner(spinner_section, Constants.Arrays.sections);
        setupSpinner(spinner_semister, Constants.Arrays.semisters);
        setupSpinner(spinner_college, Constants.Arrays.college);


        edt_name_first.addTextChangedListener(this);
        edt_name_last.addTextChangedListener(this);

        edt_phone.addTextChangedListener(this);

        spinner_college.setOnItemSelectedListener(this);
        spinner_brances.setOnItemSelectedListener(this);
        spinner_section.setOnItemSelectedListener(this);
        spinner_semister.setOnItemSelectedListener(this);

        sign_up.setOnClickListener(this);

        if (User.isnumbersaved(this)){

            edt_phone.setText(Save.getSharedPreferencesString(this,Save.phonenumber,""));
            edt_phone.setEnabled(false);
        }else{
            edt_phone.setEnabled(true);
        }

    }


    public void setupSpinner(Spinner spinner, String[] data) {
        ArrayList<String> entries = new ArrayList<String>(Arrays.asList(data));
        if (!entries.isEmpty()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    R.layout.spinner_text, R.id.tv_spiner, entries);
            spinner.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        manageSelections(position, parent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void manageSelections(int position, AdapterView<?> view) {

        if (position == 0) {
            switch (view.getId()) {
                case R.id.spinner_branch:
                    val_branch = null;
                    break;

                case R.id.spinner_section:
                    ;
                    val_section = null;
                    break;

                case R.id.spinner_semister:
                    val_sem = null;
                    break;

                case R.id.spinner_college:
                    val_college = null;
                    animateSpinners(false);
                    break;

            }
        } else {
            switch (view.getId()) {
                case R.id.spinner_branch:
                    val_branch = Constants.Arrays.branches[position];
                    break;

                case R.id.spinner_section:
                    val_section = Constants.Arrays.sections[position];
                    break;

                case R.id.spinner_semister:
                    val_sem = Constants.Arrays.semisters[position];
                    break;

                case R.id.spinner_college:
                    val_college = Constants.Arrays.college[position];
                    if (position == 2) {
                        animateSpinners(false);
                        val_branch = "NA";
                        val_sem = "NA";
                        val_section = "NA";
                    } else {
                        animateSpinners(true);
                    }

                    break;

            }
        }

        validateForm(false);
    }

    private void animateSpinners(boolean show) {

        if (!show) {
            if (spinner_brances.getVisibility() != View.GONE) {
                TransitionManager.beginDelayedTransition(spinner_container);
                spinner_brances.setVisibility(View.GONE);
                spinner_section.setVisibility(View.GONE);
                spinner_semister.setVisibility(View.GONE);

            }
        } else {
            if (spinner_brances.getVisibility() != View.VISIBLE) {
                setupSpinner(spinner_section, Constants.Arrays.sections);
                setupSpinner(spinner_semister, Constants.Arrays.semisters);
                setupSpinner(spinner_brances, Constants.Arrays.branches);

                TransitionManager.beginDelayedTransition(spinner_container);
                spinner_section.setVisibility(View.VISIBLE);
                spinner_brances.setVisibility(View.VISIBLE);
                spinner_semister.setVisibility(View.VISIBLE);
                Log.d(TAG, "animateSpinners:  3");


                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        }

    }

    public void validateForm(boolean inline) {
        if (isFormValid(inline)) {
            sign_up.setBackgroundColor(Color.parseColor("#02E16B"));
            if (inline) {
                saveUser();
            }
        } else {
            sign_up.setBackgroundColor(Color.parseColor("#FF6551"));
        }
    }

    public boolean isFormValid(boolean inline) {

        if (inline) {

            if (val_name_first == null) {
                edt_name_first.setError("Dosen't seem like your first name \uD83D\uDE25");

                return false;
            } else if (val_name_last == null) {
                edt_name_last.setError("Dosen't seem like your last name \uD83D\uDE25");
                return false;
            } else if (val_phone == 0) {
                edt_phone.setError("Invalid phone number \uD83D\uDE25");
                return false;
            } else if (val_college == null) {
                Utils.Notify.toast(this, "Invalid College");
                return false;
            } else if (val_branch == null) {
                Utils.Notify.toast(this, "Invalid Branch");
                return false;
            } else if (val_section == null) {
                Utils.Notify.toast(this, "Invalid Section");
                return false;
            } else if (val_sem == null) {
                Utils.Notify.toast(this, "Invalid Semester");
                return false;
            } else if (val_name_first.length() == 0) {
                edt_name_first.setError("Dosen't seem like your first name \uD83D\uDE25");
                return false;
            } else if (val_name_last.length() == 0) {
                edt_name_last.setError("Dosen't seem like your last name \uD83D\uDE25");
                return false;
            } else {
                return true;
            }
        } else {
            if (val_name_first == null) {

                return false;
            } else if (val_name_last == null) {

                return false;
            } else if (val_phone < 0) {

                return false;
            } else if (val_college == null) {

                return false;
            } else if (val_branch == null) {

                return false;
            } else if (val_section == null) {
                return false;
            } else if (val_sem == null) {
                return false;
            } else if (val_name_first.length() == 0) {
                return false;
            } else if (val_name_last.length() == 0) {
                return false;
            } else {
                return true;
            }
        }


    }


    public void saveUser() {
        user = new User();
        user.setBranch(val_branch);
        user.setSection(val_section);
        user.setSemester(val_sem);
        user.setName(val_name_first + " " + val_name_last);
        user.setFirst_name(val_name_first);
        user.setLast_name(val_name_last);
        user.setPhonenumber(String.valueOf(val_phone));
        user.setCollege(val_college);


        sweetAlertDialog.show();

        WebUtils.postJsonData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                user = new Gson().fromJson(response.toString(), User.class);
                Save.putSharedPreferencesString(OnboardUser.this, Save.ME, user.toString());
                Log.d(TAG, "saveUser: " + user.toString());
                User.login(OnboardUser.this, true);
                sweetAlertDialog.dismiss();

                sweetAlertDialog = new SweetAlertDialog(OnboardUser.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("Welcome! " + user.getFirst_name());
                sweetAlertDialog.setContentText("Greetings from the Developer & Malhar Committee.");
                sweetAlertDialog.showCancelButton(false);
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.setConfirmText("Proceed!");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
sweetAlertDialog.dismiss();
                        Utils.launch(OnboardUser.this, Home.class, true);

                    }
                });

                sweetAlertDialog.show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                sweetAlertDialog = new SweetAlertDialog(OnboardUser.this, SweetAlertDialog.ERROR_TYPE);
                sweetAlertDialog.setTitleText("Oops...");
                sweetAlertDialog.setContentText("Something went wrong!");
                sweetAlertDialog.show();
            }
        }, WebUtils.base_domain+"malhar/user", new Gson().toJson(user).toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        val_name_first = edt_name_first.getText().toString();
        val_name_last = edt_name_last.getText().toString();
        if (edt_phone.getText().toString().trim().length() > 0) {
            val_phone = Long.valueOf(edt_phone.getText().toString());
        } else {
            val_phone = 0;
        }

        validateForm(false);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_up:
                validateForm(true);
                break;
        }
    }


}
