package badarkhe.gaurav.andorid.apps.com.malhar2k17.registration;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Save;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.Home;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Admin on 01-11-2016.
 */
public class VerifyUser extends AppCompatActivity {

    private static final String TAG = VerifyUser.class.getName();
    @BindView(R.id.input_phone)
    EditText edt_phone;
    @BindView(R.id.input_otp)
    EditText edt_otp;

    @BindView(R.id.sign_up)
    Button sign_up;

    String otp = null;
    boolean isotpfequested = false;
    SweetAlertDialog sweetAlertDialog;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_use);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(Color.TRANSPARENT);

        toolbar.setTitle("Verify Phone Numbers");
       setSupportActionBar(toolbar);

        edt_otp.setEnabled(false);

        if (User.isnumbersaved(this)){
            Utils.launch(this,OnboardUser.class,true);
        }

        GifImageView gifImageView = (GifImageView) findViewById(R.id.giv_demo);
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.malharanim);
            gifImageView.setImageDrawable(gifDrawable);
        } catch (Resources.NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isotpfequested) {
                    verifyotp();
                } else {
                    requestOtp();
                }
            }
        });
    }

    public void verifyotp() {
        if (otp.equals(edt_otp.getText().toString())) {
            // Utils.launch(this,VerifyUser.class,true);
            Save.putSharedPreferencesString(VerifyUser.this, Save.phonenumber,edt_phone.getText().toString());
            User.saveNumber(this,true);
            identifyUser();
        } else {
            Utils.Notify.toast(this, "Invalid otp");

        }
    }

    public void requestOtp() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Requesting OTP...");
        sweetAlertDialog.setContentText("Please wait");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();

        WebUtils.getStringData(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sweetAlertDialog.dismiss();
                otp = response;
                edt_phone.setEnabled(false);
                isotpfequested = true;
                sign_up.setText("Verify OTP");
                edt_otp.setEnabled(true);
                Utils.Notify.toast(VerifyUser.this, "Otp is sent to your number");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                otp = null;
                error.printStackTrace();
                isotpfequested = false;
                sweetAlertDialog.dismiss();
                Utils.Notify.toast(VerifyUser.this, "Error Occured otp not generated");
            }
        }, WebUtils.base_domain + "malhar/user/verify/" + edt_phone.getText().toString());
    }

    public void identifyUser() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Please wait...");
        sweetAlertDialog.setContentText("doing some magic");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
        WebUtils.getJsonData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sweetAlertDialog.dismiss();
                user = new Gson().fromJson(response.toString(), User.class);
                if (user.getName() != null) {
                    Save.putSharedPreferencesString(VerifyUser.this, Save.ME, user.toString());
                    Log.d(TAG, "saveUser: " + user.toString());
                    User.login(VerifyUser.this, true);


                    sweetAlertDialog = new SweetAlertDialog(VerifyUser.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("Welcome! " + user.getFirst_name());
                    sweetAlertDialog.setContentText("Greetings from the Developer & Malhar Committee.");
                    sweetAlertDialog.showCancelButton(false);
                    sweetAlertDialog.setCancelable(false);
                    sweetAlertDialog.setConfirmText("Proceed!");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            Utils.launch(VerifyUser.this, Home.class, true);

                        }
                    });

                    sweetAlertDialog.show();
                }else{
                    Utils.launch(VerifyUser.this,OnboardUser.class,true);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sweetAlertDialog.dismiss();
                Utils.launch(VerifyUser.this, OnboardUser.class, true);
                Utils.Notify.toast(VerifyUser.this, "Error Occured");

            }
        }, WebUtils.base_domain + "malhar/user/exists/" + edt_phone.getText().toString());

    }
}

