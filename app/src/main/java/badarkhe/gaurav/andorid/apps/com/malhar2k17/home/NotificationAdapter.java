package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.CommonUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.WebUtils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.home.ViewHolders.NotificationViewHolder;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 23-12-2016.
 */

public class NotificationAdapter extends RecyclerView.Adapter<ViewHolders.NotificationViewHolder> {

    Context c;
    ArrayList<MalharNotification> events = new ArrayList<>();
    String evemt_type;

    public NotificationAdapter(ArrayList<MalharNotification> mevents, Context context, String type) {
        c = context;
        events = mevents;
        evemt_type = type;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);

        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, final int position) {

        final MalharNotification notification = events.get(position);
        if (notification.getImageUri() == null) {
            holder.notiimage.setVisibility(View.GONE);
        } else {
            Glide.with(c).load(notification.getImageUri())
                    .thumbnail(0.5f)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.notiimage);
            holder.notiimage.setVisibility(View.VISIBLE);

        }

        holder.body_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteEvent(notification, position);
                return false;
            }
        });

        holder.tittle_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteEvent(notification, position);
                return false;
            }
        });

        holder.tittle_text.setText(notification.getTittle());
        holder.body_text.setText(notification.getBody());

        List<String> pics = new ArrayList<>();
        if (events.get(position).getTittle().toLowerCase().contains("drama") || events.get(position).getBody().toLowerCase().contains("drama")) {
            pics = Arrays.asList(Constants.Arrays.Pics.drama);
        }
        if (events.get(position).getTittle().toLowerCase().contains("comedy") || events.get(position).getBody().toLowerCase().contains("comedy")) {
            pics = Arrays.asList(Constants.Arrays.Pics.comedy);
        }
        if (events.get(position).getTittle().toLowerCase().contains("dance") || events.get(position).getBody().toLowerCase().contains("dance")) {
            pics = Arrays.asList(Constants.Arrays.Pics.dance);
        }
        if (events.get(position).getTittle().toLowerCase().contains("fasion") || events.get(position).getBody().toLowerCase().contains("fashion") ||
                events.get(position).getTittle().toLowerCase().contains("fasion") || events.get(position).getBody().toLowerCase().contains("fashion")) {
            pics = Arrays.asList(Constants.Arrays.Pics.fasion);
        }
        if (events.get(position).getTittle().toLowerCase().contains("rock") || events.get(position).getBody().toLowerCase().contains("rock")) {
            pics = Arrays.asList(Constants.Arrays.Pics.rock_band);
        }
        if (events.get(position).getTittle().toLowerCase().contains("singing") || events.get(position).getBody().toLowerCase().contains("singing")) {
            pics = Arrays.asList(Constants.Arrays.Pics.singing);
        }
        if (events.get(position).getTittle().toLowerCase().contains("sports") || events.get(position).getBody().toLowerCase().contains("sports")) {
            pics.add(Constants.Arrays.Pics.sports);
        }


        if (pics.size() > 0) {

            holder.thumbnail.setVisibility(View.VISIBLE);
            Glide.with(c).load(pics.get(0) == Constants.Arrays.Pics.sports ? pics.get(0) : Constants.Arrays.base_url + pics.get(new Random().nextInt(pics.size() - 1)))
                    .thumbnail(0.5f)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(Utils.getScreenWidth(c) / 3, Utils.getScreenWidth(c) / 3)
                    .into(holder.thumbnail);


        } else {
            holder.thumbnail.setVisibility(View.GONE);
        }


    }

    private void deleteEvent(final MalharNotification notification, final int positon) {

        CommonUtils.Dialogs.showDialog(c, "Delete Notificaiton?", notification.getTittle(), "Yes", SweetAlertDialog.WARNING_TYPE, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                WebUtils.postStringData(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        events.remove(positon);
                        notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, "Failed to delete!", Toast.LENGTH_SHORT).show();
                    }
                }, WebUtils.base_domain + "malhar/notify/delete/" + notification.getId());
            }
        }).show();
    }


    @Override
    public int getItemCount() {
        return events.size();
    }
}
