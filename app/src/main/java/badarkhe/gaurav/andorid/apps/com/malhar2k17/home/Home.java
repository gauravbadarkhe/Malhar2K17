package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.SampleApplication;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer.SlideshowDialogFragment;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.OnboardUser;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.VerifyUser;

public class Home extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!User.isLoggedIn(this)) {
            Utils.launch(this, OnboardUser.class, true);
        }

        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_action_name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(4);
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#3E3F47"));
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setText("COLLIGIATE(" + SampleApplication.rgcer + ")");
                        break;
                    case 1:
                        tab.setText("INTER-COLLIGIATE(" + SampleApplication.others + ")");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void aboutTeam() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.about_app, null);
        dialogBuilder.setView(dialogView);
        TextView editText = (TextView) dialogView.findViewById(R.id.about_text);
        editText.setMovementMethod(LinkMovementMethod.getInstance());
        editText.setText(Html.fromHtml("<p><b>About App</b></p>\n" +
                "<p>The app has been developed to bring everything at one place. It will help students to be updated about all trends happening in Malhar.</p>\n" +
                "<p>Special Thanks to<b>&nbsp;<a href=https://www.instagram.com/sgrawesome>Sagar Bhagat</a>, <a href=https://www.instagram.com/gauravbadarkhe>Gaurav Badarkhe</a>, " +
                "<a href=https://www.instagram.com/krunal.n12>Krunal Nampalliwar</a> &amp; Team Malhar</b> for their contribution in making this app.</p>"));

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_team) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isappintro", true);
            bundle.putBoolean("isFromHome", true);
            bundle.putInt("position", 3);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();

            newFragment.setArguments(bundle);
            newFragment.show(ft, "slideshow");
            return true;
        }

        if (id == R.id.about_text) {
            aboutTeam();
        }

        if (id == R.id.action_2016) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isappintro", true);
            bundle.putBoolean("isFromHome", true);
            bundle.putInt("position", 1);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();

            newFragment.setArguments(bundle);
            newFragment.show(ft, "slideshow");
            return true;
        }

        if (id == R.id.action_logout) {
            User.login(this, false);
            User.saveNumber(this, false);
            Utils.launch(this, VerifyUser.class, true);
            return true;
        }


        if (id == R.id.action_fb) {
            Utils.launchLink(this, "https://www.facebook.com/malhar.malhar.1466");
            return true;
        }

        if (id == R.id.action_insta) {
            Utils.launchLink(this, "https://www.instagram.com/malhar_2k17/");
            return true;
        }

        if (id == R.id.action_email) {
            Utils.launchLink(this, "mailto:rgcermalhar@gmail.com");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return Events_Fragment.newInstance("all");
            } else if (position == 1) {
                return Events_Fragment.newInstance("others");
            } else {
                return NotificationFragment.newInstance("Notification");
            }


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "COLLIGIATE";
                case 1:
                    return "INTER-COLLIGIATE";
                case 2:
                    return "Notification";

            }
            return null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCount(User u) {
        tabLayout.getTabAt(0).setText("COLLIGIATE(" + SampleApplication.rgcer + ")");
        tabLayout.getTabAt(1).setText("INTER-COLLIGIATE(" + SampleApplication.others + ")");
        tabLayout.getTabAt(2).setText("Notification(" + SampleApplication.me + ")");

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
