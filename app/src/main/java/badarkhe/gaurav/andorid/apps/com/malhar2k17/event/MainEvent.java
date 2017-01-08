package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.transitionseverywhere.TransitionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.CommonUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.DateUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.firebase.Config;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer.Image;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer.SlideshowDialogFragment;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.OnboardUser;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.mrapp.android.bottomsheet.BottomSheet;

/**
 * Created by Admin on 22-10-2016.
 */
public class MainEvent extends AppCompatActivity {

    private static final String TAG = MainEvent.class.getName();
    @BindView(R.id.btnFollow)
    Button txt_time;

    @BindView(R.id.event_name)
    TextView txt_event_name;

    @BindView(R.id.event_orginisor)
    TextView txt_event_orginisor;

    @BindView(R.id.event_description)
    TextView txt_event_description;

    @BindView(R.id.msg)
    TextView txt_event_msg;

    @BindView(R.id.event_card)
    CardView card;

    @BindView(R.id.vUserProfileRoot)
    LinearLayout liner_parent;


    User user = new User();
    SweetAlertDialog sweetAlertDialog;
    RecyclerView recyclerView;

    Event e = null;
    boolean isregistered = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!User.isLoggedIn(this)) {
            Utils.launch(this, OnboardUser.class, true);
        }
        setContentView(R.layout.content_event);
        ButterKnife.bind(this);
        user.getUser(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setStatusBarColor(Color.parseColor("#3E3F47"));

        recyclerView = (RecyclerView) findViewById(R.id.rvUserProfile);

//        EventAdapter adapter = new EventAdapter(this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager lm = new LinearLayoutManager((this), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(lm);

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading..");
        sweetAlertDialog.setContentText("Please wait");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();


        final Handler h = new Handler();
        final int delay = 1000; //milliseconds
        final long date = new Date(1485369000000L).getTime() - System.currentTimeMillis();
        final long[] timerdelay = {0};
//        h.postDelayed(new Runnable() {
//            public void run() {
//
////                //do something
////                if (isregistered) {
////                    txt_time.setText("--REGISTERED--\n" + DateUtils.parseTime((date) - timerdelay[0]) + "\nTime left");
////                    txt_time.setBackgroundColor(Color.GRAY);
////                } else {
//                    txt_time.setText("--REGISTER NOW--\n" + DateUtils.parseTime((date) - timerdelay[0]) + "\nTime left");
////                }
//
//                // Log.d("D1", Utils.ymwdhmsDifference(new Date(System.currentTimeMillis()),new Date(date)));
//                //Log.d("D2", DateUtils.parseTime(7776000000L-timerdelay[0]));
//                timerdelay[0] = timerdelay[0] + 1000;
//                h.postDelayed(this, delay);
//
//            }
//        }, delay);

        inflateEvent();

        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRegistrationTypes();

//                if (isregistered) {
//                    Utils.Notify.toast(MainEvent.this, "Contact Organiser to cancel registration");
//                } else {
//                    FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(e.getEvent_id()));
//                    if (new User().getUser(MainEvent.this).getCollege().equals("RGCER")) {
//                        registerForEvent();
//                    } else {
//                        if (getIntent().getStringExtra("type").equals("others")) {
//                            registerForEvent();
//                        } else {
//                            Utils.Notify.toast(MainEvent.this, "This event is RGCER exclusive.");
//                        }
//                    }
//                }
            }
        });


    }

    public void getRegistrationTypes() {
        Log.d(TAG, "getRegistrationTypes: 1");
        WebUtils.getJsonData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "getRegistrationTypes: 2");
                try {
                    parseEvent(response.getJSONArray("events").getJSONObject(0));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                    ;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getRegistrationTypes: 3");
                error.printStackTrace();
            }
        }, "http://zipd.in/events.json");
    }

    private void parseEvent(JSONObject response) throws JSONException {
        Log.d(TAG, "parseEvent: " + response.toString());
        List<RegisterEvent> registerEvents = new ArrayList<>();

        if (e.getEvent_name().toLowerCase().contains("drama")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("drama").toString(), RegisterEvent.class);
        }
        if (e.getEvent_name().toLowerCase().contains("comedy")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("comedy").toString(), RegisterEvent.class);
        }
        if (e.getEvent_name().toLowerCase().contains("dance")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("dance").toString(), RegisterEvent.class);
        }
        if (e.getEvent_name().toLowerCase().contains("fasion") || e.getEvent_name().toLowerCase().contains("fashion")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("fasion").toString(), RegisterEvent.class);
        }
        if (e.getEvent_name().toLowerCase().contains("rock")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("rock").toString(), RegisterEvent.class);
        }
        if (e.getEvent_name().toLowerCase().contains("singing")) {
            registerEvents = CommonUtils.toList(response.getJSONArray("singing").toString(), RegisterEvent.class);
        }

        if (registerEvents != null) {
            Log.d(TAG, "getRegistrationTypes: 4");
            final List<RegisterEvent> finalRegisterEvents = registerEvents;
            BottomSheet bottomSheet = CommonUtils.makeSheet(registerEvents, this);
            bottomSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (finalRegisterEvents.get(position).getIsactive()) {
                        Log.d(TAG, "onItemClick: " + position);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalRegisterEvents.get(position).getLink()));
                        startActivity(browserIntent);
                        // Toast.makeText(MainEvent.this, "Ba Dum tush!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainEvent.this, "Registration of this category are unavailable!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            bottomSheet.show();
        } else {
            Log.d(TAG, "getRegistrationTypes: 5");
            Toast.makeText(MainEvent.this, "Ba Dum tushss!!!", Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "getRegistrationTypes: 6");
    }

    public void inflateEvent() {

        WebUtils.getJsonData(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                sweetAlertDialog.dismiss();

                e = new Gson().fromJson(response.toString(), Event.class);
                if (e.getEvent_name() != null) {
                    txt_event_name.setText(e.getEvent_name());
                    txt_event_orginisor.setText("-" + e.getOrginiser());
                    txt_event_description.setText(e.getDescription());

                    isregistered = e.isregistered;
                    if (e.getMessage() != null) {
                        if (e.getMessage().trim().length() > 0) {
                            txt_event_msg.setText("Message from Organisers:-\n\n" + e.getMessage());

                            TransitionManager.beginDelayedTransition(liner_parent);
                            card.setVisibility(View.VISIBLE);
                        }
                    } else {
                        card.setVisibility(View.GONE);

                    }
                    setupGalary(e);
                } else {
                    finish();
                    Utils.Notify.toast(MainEvent.this, "No data to show");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                sweetAlertDialog.dismiss();
                Utils.Notify.toast(MainEvent.this, "Opps!! error occured.");
                finish();
            }
        }, WebUtils.base_domain + "malhar/event/" + getIntent().getLongExtra("event_id", 0) + "?uid=" + user.getUser(this).getUid());
    }

    public void registerSolo() {
        if (e != null) {
            WebUtils.postStringData(new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    isregistered = true;
                    Log.d(TAG, "onResponse: " + response);
                    sweetAlertDialog.dismiss();
                    sweetAlertDialog = new SweetAlertDialog(MainEvent.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("Hey! " + user.getFirst_name());
                    sweetAlertDialog.setContentText("Registration for " + e.getEvent_name() + " is conformed.");
                    sweetAlertDialog.showCancelButton(false);
                    sweetAlertDialog.setCancelable(false);
                    sweetAlertDialog.setConfirmText("Proceed!");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();


                        }
                    });
                    sweetAlertDialog.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    sweetAlertDialog.dismiss();
                    error.printStackTrace();
                    Utils.Notify.toast(MainEvent.this, "Opps!! error occured.");

                }
            }, WebUtils.base_domain + "malhar/event/interact?uid=" + user.getUser(this).getUid() + "&event_id=" + e.getEvent_id());
        }
    }


    public void startSecondaryregistration(int min, int limit) {

        Intent intent = new Intent(this, SecondaryRegistration.class);
        intent.putExtra("event_id", e.getEvent_id());
        intent.putExtra("limit", limit);
        intent.putExtra("min", min);
        Utils.launch(this, intent, false);
    }


    public void registerForEvent() {
        //sweetAlertDialog.show();

        switch (e.getRegistration_type()) {
            case 1:
                registerSolo();
                break;
            case 2:
                startSecondaryregistration(1, 2);
                break;
            case 3:
                startSecondaryregistration(1, 20);
                break;

            case 4:
                startSecondaryregistration(2, 20);
                break;


        }


    }

    public void setupGalary(final Event e) {

//        List<String> pics = new ArrayList<>();
//
//
//        if (e.getEvent_name().toLowerCase().contains("drama")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.drama);
//        }
//        if (e.getEvent_name().toLowerCase().contains("comedy")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.comedy);
//        }
//        if (e.getEvent_name().toLowerCase().contains("dance")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.dance);
//        }
//        if (e.getEvent_name().toLowerCase().contains("fashion") || e.getEvent_name().toLowerCase().contains("fasion")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.fasion);
//        }
//        if (e.getEvent_name().toLowerCase().contains("rock")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.rock_band);
//        }
//        if (e.getEvent_name().toLowerCase().contains("singing")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.singing);
//        }
//
//        if (!e.getImages().isEmpty()) {
//            for (String s : e.getImages()) {
//                pics.add( s);
//            }
//        }


        ImageView img = (ImageView) findViewById(R.id.ivUserProfilePhoto);

        String image = e.getImages().get(new Random().nextInt(e.getImages().size() - 1));
        if (image.startsWith("COLLIGIATE") || image.startsWith("INTERCOLLIGIATE")) {
            image = Constants.Arrays.base_url + image;
        }
        if (e.getImages().size() > 0) {
            Glide.with(this).load(image)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        } else {
            Utils.Notify.toast(this, "No images for " + e.getEvent_name());
        }

        final EvenProfileAdapter userPhotosAdapter = new EvenProfileAdapter(this, e.getImages());


        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userPhotosAdapter);
        userPhotosAdapter.setLockedAnimations(true);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                userPhotosAdapter.setLockedAnimations(true);
            }
        });

        final List<String> finalPics = e.getImages();
        recyclerView.addOnItemTouchListener(new EvenProfileAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new EvenProfileAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", new Image(finalPics, e.getEvent_name(), e.getDescription()));
                bundle.putInt("position", position);
                bundle.putBoolean("isappintro", false);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();

                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
