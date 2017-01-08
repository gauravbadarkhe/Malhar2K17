package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.transitionseverywhere.TransitionManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.SampleApplication;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.event.Event;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 21-10-2016.
 */
public class Events_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    EventAdapter adapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    User user;

    SweetAlertDialog sweetAlertDialog;
    String type;



    public static Events_Fragment newInstance(String type) {
        Events_Fragment fragment = new Events_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        type = getArguments().getString(ARG_SECTION_NUMBER);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.ev_events);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        user = new User().getUser(getActivity());
        adapter = new EventAdapter(new ArrayList<Event>(), getActivity(),"all");
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inflateEvent();

            }
        });
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);


        inflateEvent();
        return rootView;
    }

    public void inflateEvent() {
        sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading..");
        sweetAlertDialog.setContentText("Please wait");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
        refreshLayout.setRefreshing(true);
        WebUtils.getJsonArrayData(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sweetAlertDialog.dismiss();
                refreshLayout.setRefreshing(false);
                try {
                    parseEvents(response);



                } catch (JSONException e) {
                    e.printStackTrace();
                    Utils.Notify.toast(getActivity(), "Oops!! Error occurred.");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sweetAlertDialog.dismiss();
                error.printStackTrace();
            }
        }, WebUtils.base_domain+"malhar/event/for/" + type + "?uid=" + user.getUser(getActivity()).getUid());

    }

    public void parseEvents(JSONArray array) throws JSONException {
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            events.add(new Gson().fromJson(array.getJSONObject(i).toString(), Event.class));
        }
        if (events.size() > 0) {
            recyclerView.setAdapter(null);
            adapter = new EventAdapter(events, getActivity(),type);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        } else {
           // Utils.Notify.toast(getActivity(), "No  events right now....");
        }

        switch (type) {
            case "all":
                SampleApplication.rgcer = events.size();

                break;

            case "others":
                SampleApplication.others = events.size();
                break;

            case "me":
                SampleApplication.me = events.size();
                break;
        }

        EventBus.getDefault().post(new User());


    }

}
