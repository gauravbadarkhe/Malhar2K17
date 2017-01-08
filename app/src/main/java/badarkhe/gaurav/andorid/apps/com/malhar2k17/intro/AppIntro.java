package badarkhe.gaurav.andorid.apps.com.malhar2k17.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.Home;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer.Image;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer.SlideshowDialogFragment;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;

/**
 * Created by Admin on 31-10-2016.
 */
public class AppIntro extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("notifyall");
            if (User.isLoggedIn(this)) {
                Utils.launch(this, Home.class, true);
            } else {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isappintro", true);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();

                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
        }
    }
}
