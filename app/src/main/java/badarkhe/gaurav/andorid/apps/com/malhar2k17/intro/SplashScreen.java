package badarkhe.gaurav.andorid.apps.com.malhar2k17.intro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.firebase.Config;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.firebase.NotificationUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.Home;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.OnboardUser;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Admin on 01-11-2016.
 */
public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.input_phone)
    EditText edt_phone;
    @BindView(R.id.input_otp)
    EditText edt_otp;

    @BindView(R.id.sign_up)
    Button sign_up;



    private static final String TAG = SplashScreen.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_use);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(Color.TRANSPARENT);

        //toolbar.setTitle("Verify Phone Numbers");
        //setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        edt_otp.setVisibility(View.GONE);
        edt_phone.setVisibility(View.GONE);
        sign_up.setVisibility(View.GONE);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.giv_demo);
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.malharanim);
            gifImageView.setImageDrawable(gifDrawable);
        } catch (Resources.NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block+
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.launch(SplashScreen.this, AppIntro.class, true);
            }
        }, 4000);


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
                    String regId = pref.getString("regId", null);
                    Log.d(TAG, "onReceive: "+regId);

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();


                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
