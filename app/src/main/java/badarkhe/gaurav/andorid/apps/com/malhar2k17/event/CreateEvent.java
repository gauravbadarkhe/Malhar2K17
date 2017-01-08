package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.Manifest.permission.READ_CONTACTS;


public class CreateEvent extends AppCompatActivity {


    @BindView(R.id.input_name)
    EditText edt_name;
    @BindView(R.id.input_description)
    EditText edt_descr;
    @BindView(R.id.input_orginiser)
    EditText edt_orginisor;
    @BindView(R.id.input_message)
    EditText edt_message;
    @BindView(R.id.input_photo)
    EditText edt_photo;

    @BindView(R.id.btn_register)
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_event);
        ButterKnife.bind(this);



        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Event e =  new Event();
                e.setEvent_name(edt_name.getText().toString());
                e.setDescription(edt_descr.getText().toString());
                e.setOrginiser(edt_orginisor.getText().toString());
                e.setMessage(edt_message.getText().toString());
                e.setPhoto_count(Integer.parseInt(edt_photo.getText().toString()));

                final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(CreateEvent.this,SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.setTitleText("Saving..");
                sweetAlertDialog.setContentText("Please wait");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.show();

                WebUtils.postJsonData(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        sweetAlertDialog.dismiss();
                        Utils.Notify.toast(CreateEvent.this,"Event Saved!!");
                        Utils.launch(CreateEvent.this,CreateEvent.class,true);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        sweetAlertDialog.dismiss();
                        Utils.Notify.toast(CreateEvent.this,"Failed to save!!");
                    }
                },WebUtils.base_domain+"malhar/event/",new Gson().toJson(e).toString());
            }
        });

    }


}

