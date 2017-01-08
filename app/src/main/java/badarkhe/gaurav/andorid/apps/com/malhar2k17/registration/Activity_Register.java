package badarkhe.gaurav.andorid.apps.com.malhar2k17.registration;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import java.util.ArrayList;
import java.util.Arrays;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;

public class Activity_Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinner_brances, spinner_section, spinner_semister;
    ViewGroup transitionsContainer, transitionsContainer_spinner;
    EditText text;
    ImageButton button;
    boolean visible;
    TextView tv_hi;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        transitionsContainer = (ViewGroup) findViewById(R.id.parent_contaner);
        text = (EditText) transitionsContainer.findViewById(R.id.edt_name);
        button = (ImageButton) transitionsContainer.findViewById(R.id.button);

        transitionsContainer_spinner = (ViewGroup) findViewById(R.id.linear_container_spinner);
        spinner_brances = (Spinner) transitionsContainer_spinner.findViewById(R.id.spinner_branch);
        spinner_section = (Spinner) transitionsContainer_spinner.findViewById(R.id.spinner_section);
        spinner_semister = (Spinner) transitionsContainer_spinner.findViewById(R.id.spinner_semister);

        tv_hi = (TextView) transitionsContainer_spinner.findViewById(R.id.tv_hi);



        initiate();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:

                proceed();
                break;
        }

    }

    private void proceed(){

       // TransitionManager.beginDelayedTransition(transitionsContainer);

name = text.getText().toString().replace("Hi,","");
        animateButton();
    }

    private void animateButton(){


        RelativeLayout.LayoutParams lp_big_edt = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        lp_big_edt.removeRule(RelativeLayout.ALIGN_BOTTOM);
        lp_big_edt.removeRule(RelativeLayout.LEFT_OF);
        lp_big_edt.removeRule(RelativeLayout.START_OF);


        RelativeLayout.LayoutParams lp_small_edt = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        lp_big_edt.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.button);
        lp_big_edt.addRule(RelativeLayout.LEFT_OF,R.id.button);
        lp_big_edt.addRule(RelativeLayout.START_OF,R.id.button);


        RelativeLayout.LayoutParams lp_big = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                90);

        lp_big.addRule(RelativeLayout.BELOW,R.id.linear_container_spinner);
        lp_big.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp_big.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp_big.removeRule(RelativeLayout.ALIGN_PARENT_END);


        RelativeLayout.LayoutParams lp_small = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                60);
        lp_small.removeRule(RelativeLayout.BELOW);
        lp_small.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp_small.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp_small.addRule(RelativeLayout.ALIGN_PARENT_END);


        TransitionManager.beginDelayedTransition(transitionsContainer,
                new ChangeBounds().setPathMotion(new ArcMotion()).setDuration(500));





        visible = !visible;

        transitionsContainer_spinner.setVisibility(visible ? View.VISIBLE : View.GONE);
        spinner_brances.setVisibility(visible ? View.VISIBLE : View.GONE);
        spinner_semister.setVisibility(visible ? View.VISIBLE : View.GONE);
        spinner_section.setVisibility(visible ? View.VISIBLE : View.GONE);

        button.setLayoutParams(visible?lp_big:lp_small);
        text.setLayoutParams(visible?lp_small_edt:lp_big_edt);




    }

    private void initiate() {

        setupSpinner(spinner_brances, Constants.Arrays.branches);
        setupSpinner(spinner_section, Constants.Arrays.sections);
        setupSpinner(spinner_semister, Constants.Arrays.semisters);

        spinner_brances.setOnItemSelectedListener(this);
        button.setOnClickListener(this);
    }


    private void manageSelections(int position, AdapterView<?> view) {

        if (position == 0) {
            Utils.Notify.toast(this, "Invalid Selcetion");
        }
        switch (view.getId()) {
            case R.id.spinner_branch:

                break;

            case R.id.spinner_section:
                break;

            case R.id.spinner_semister:
                break;

        }
    }

}

