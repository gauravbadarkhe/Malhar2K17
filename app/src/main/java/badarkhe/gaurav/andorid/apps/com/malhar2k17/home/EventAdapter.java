package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Constants;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.event.Event;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.event.MainEvent;

/**
 * Created by Admin on 21-10-2016.
 */
public class EventAdapter extends RecyclerView.Adapter<ViewHolders.EventViewHolder> {

    Context c;
    ArrayList<Event> events = new ArrayList<>();
    String evemt_type;

    public EventAdapter(ArrayList<Event> mevents, Context context, String type) {
        c = context;
        events = mevents;
        evemt_type = type;
    }

    @Override
    public ViewHolders.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event, parent, false);

        return new ViewHolders.EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolders.EventViewHolder holder, final int position) {


//        if (events.get(position).getEvent_name()==null){
//            events.remove(position);
//            notifyDataSetChanged();
//        }else{
        holder.cardView.setBackgroundColor(Color.parseColor(Constants.Arrays.colors[new Random().nextInt(Constants.Arrays.colors.length)]));
        // holder.event_count.setText(String.valueOf(new Random().nextInt(9)));
        // holder.event_logo.setBackgroundResource(Constants.Arrays.thumnails[new Random().nextInt(Constants.Arrays.thumnails.length)]);

// List<String> pics = new ArrayList<>();
//
//
//        if (events.get(position).getEvent_name().toLowerCase().contains("drama")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.drama);
//        }
//        if (events.get(position).getEvent_name().toLowerCase().contains("comedy")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.comedy);
//        }
//        if (events.get(position).getEvent_name().toLowerCase().contains("dance")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.dance);
//        }
//        if (events.get(position).getEvent_name().toLowerCase().contains("fasion") || events.get(position).getEvent_name().toLowerCase().contains("fashion")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.fasion);
//        }
//        if (events.get(position).getEvent_name().toLowerCase().contains("rock")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.rock_band);
//        }
//        if (events.get(position).getEvent_name().toLowerCase().contains("singing")) {
//            pics = Arrays.asList(Constants.Arrays.Pics.singing);
//        }
//
//        if (!events.get(position).getImages().isEmpty()) {
//            for (String s : events.get(position).getImages()) {
//                pics.add(s);
//            }
//
//        }



        if (events.get(position).getImages().size() > 0) {
            String image = events.get(position).getImages().get(new Random().nextInt(events.get(position).getImages().size() - 1));
            if (image.startsWith("COLLIGIATE") || image.startsWith("INTERCOLLIGIATE")) {
                image = Constants.Arrays.base_url + image;
            }

            Glide.with(c).load(image)
                    .thumbnail(0.5f)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(Utils.getScreenWidth(c) / 3, Utils.getScreenWidth(c) / 3)
                    .into(holder.event_logo);
        }
        //Data inflator...
        holder.event_name.setText(events.get(position).getEvent_name());
        holder.event_subtittle.setText(events.get(position).getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, MainEvent.class);
                intent.putExtra("event_id", events.get(position).getEvent_id());
                intent.putExtra("type", evemt_type);
                Utils.launch(c, intent, false);

            }
        });
//        }

    }


    @Override
    public int getItemCount() {
        return events.size();
    }
}
