package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;

/**
 * Created by Admin on 21-10-2016.
 */
public class ViewHolders {

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView event_logo;
        TextView event_name, event_subtittle;
        CardView cardView;

        public EventViewHolder(View itemView) {
            super(itemView);

            event_logo = (ImageView) itemView.findViewById(R.id.thumbnail);
            event_name = (TextView) itemView.findViewById(R.id.title_text);
            event_subtittle = (TextView) itemView.findViewById(R.id.sub_tittle);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail, notiimage;
        TextView tittle_text, body_text;
        CardView cardView;

        public NotificationViewHolder(View itemView) {
            super(itemView);

            notiimage = (ImageView) itemView.findViewById(R.id.noti_image);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            tittle_text = (TextView) itemView.findViewById(R.id.title_text);
            body_text = (TextView) itemView.findViewById(R.id.body_text);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
