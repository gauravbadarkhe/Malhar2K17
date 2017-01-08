package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.Home;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SecondaryRegistration extends AppCompatActivity {

    private static final String TAG = SecondaryRegistration.class.getName();
    public static RecyclerView rv_people;

    public static EditText first_name;
    public static EditText last_name;
    public static EditText phone_number;
    public static Button addUser, submit_team;
    public static Context c;
    public static PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register Team( Max " + getIntent().getIntExtra("limit", 0) + ")");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        first_name = (EditText) findViewById(R.id.input_name_first);
        last_name = (EditText) findViewById(R.id.input_name_last);
        phone_number = (EditText) findViewById(R.id.input_phone_number);
        addUser = (Button) findViewById(R.id.add_item);
        submit_team = (Button) findViewById(R.id.submit_Team);
        rv_people = (RecyclerView) findViewById(R.id.rv_people);
        c = this;
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adapter.first_names.size() < getIntent().getIntExtra("limit", 0)) {
                    addnewUser(v);
                } else {
                    Utils.Notify.toast(SecondaryRegistration.this, "Team limit reached.");
                }

            }
        });

        submit_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adapter.first_names.size() >= getIntent().getIntExtra("min", 1) && adapter.first_names.size() <= getIntent().getIntExtra("limit", 0)) {
                    limitdone();
                } else {
                    Utils.Notify.toast(SecondaryRegistration.this, "Please add more members");
                }

            }
        });

        adapter = new PeopleAdapter(this, getIntent().getLongExtra("event_id", 0), getIntent().getIntExtra("limit", 0));
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_people.setLayoutManager(manager);
        rv_people.setAdapter(adapter);

        User u = new User().getUser(this);
        adapter.addPerson(u.getFirst_name(), u.getLast_name(), u.getPhonenumber());
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void clearDeta() {
        first_name.setText("");
        last_name.setText("");
        phone_number.setText("");
        first_name.requestFocus();

        rv_people.scrollToPosition(adapter.getItemCount());
    }

    public static void limitdone() {
        SweetAlertDialog ee = new SweetAlertDialog(c, SweetAlertDialog.WARNING_TYPE);
        ee.setTitleText("Register team?");
        ee.setContentText("your team details will be registered we will notify you about any updated.");
        ee.setConfirmText("Confirm");
        ee.setCancelText("Cancel");
        ee.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                ((SecondaryRegistration) c).finish();
                saveEvent();
            }
        });
        ee.show();
    }

    public static void saveEvent() {
//        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(c,SweetAlertDialog.PROGRESS_TYPE);
//        sweetAlertDialog.setTitleText("Registering....");
//        sweetAlertDialog.setContentText("Please wait");
//        sweetAlertDialog.setCancelable(false);
//        sweetAlertDialog.show();
        WebUtils.postJsonData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //sweetAlertDialog.dismiss();
                Utils.Notify.toast(c, "Done Team registered");
              Utils.launch(c, Home.class,true);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.Notify.toast(c, "error occured");
                Log.e(TAG, "onErrorResponse: ", error);
            }
        }, WebUtils.base_domain + "malhar/team", new Gson().toJson(adapter.getDetails()).toString());
    }


    public static void limitback() {


    }


    public void addnewUser(View c) {


        if (first_name.getText().toString().trim() == null) {
            showError(first_name, "Dosen't seem like a first name \uD83D\uDE25");
        } else if (first_name.getText().toString().length() == 0) {
            showError(first_name, "Dosen't seem like a first name \uD83D\uDE25");
        } else if (last_name.getText().toString().trim() == null) {
            showError(last_name, "Dosen't seem like a last name \uD83D\uDE25");
        } else if (last_name.getText().toString().length() == 0) {
            showError(last_name, "Dosen't seem like a last name \uD83D\uDE25");
        } else if (phone_number.getText().toString().trim() == null) {
            showError(phone_number, "Invalid phone number\uD83D\uDE25");
        } else if (phone_number.getText().toString().length() == 0) {
            showError(phone_number, "Invalid phone number \uD83D\uDE25");
        } else {
            hideSoftKeyboard(this);
            adapter.addPerson(first_name.getText().toString(), last_name.getText().toString(), phone_number.getText().toString());
        }
    }

    public void showError(EditText edt, String msg) {
        Utils.Notify.toast(this, msg);
    }

}
