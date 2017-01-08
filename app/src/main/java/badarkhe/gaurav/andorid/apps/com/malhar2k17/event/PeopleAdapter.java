package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Utils;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.R;
import badarkhe.gaurav.andorid.apps.com.malhar2k17.registration.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 30-10-2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleHolder> {

    Context c;
    long event_id;
    int limit;
    ArrayList<String> first_names = new ArrayList<>();
    ArrayList<String> last_names = new ArrayList<>();
    ArrayList<String> phones = new ArrayList<>();

    public PeopleAdapter(Context c,long mevent_id,int mlimit) {
        this.c = c;
        event_id = mevent_id;
        limit = mlimit;
    }

    public void addPerson(String first_name,String last_name, String phone) {

        if (first_names.size()-1<limit){
            first_names.add(first_name);
            last_names.add(last_name);
            phones.add(phone);
            notifyDataSetChanged();
            SecondaryRegistration.clearDeta();

            if (first_names.size()==limit){
            //   SecondaryRegistration.limitdone();
                Utils.Notify.toast(c,"Woops! member limit reached");
            }
        }else{
            Utils.Notify.toast(c,"Woops! member limit reached");
           // SecondaryRegistration.limitdone();



        }
        Log.d("", "addPerson: "+limit+" & "+first_names.size());
    }
    public People getDetails(){
      return  new People(first_names,last_names,phones,event_id,new User().getUser(c).getUid());
    }

    @Override
    public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeopleHolder(LayoutInflater.from(c).inflate(R.layout.item_people, parent,false));
    }

    @Override
    public void onBindViewHolder(PeopleHolder holder, int position) {

        holder.name.setText(first_names.get(position)+" "+last_names.get(position));
        holder.phone.setText(phones.get(position));
        holder.remove.setOnClickListener(new Clicks(position));
    }

    @Override
    public int getItemCount() {
        return first_names.size();
    }

    public class Clicks implements View.OnClickListener {
        int postion;

        public Clicks(int mpos) {
            postion = mpos;

        }

        @Override
        public void onClick(View v) {

            if (postion>0){
            first_names.remove(postion);
            last_names.remove(postion);
            phones.remove(postion);
            notifyDataSetChanged();}else{
                Utils.Notify.toast(c,"Your registration is mandatory");
            }

        }
    }

    public class PeopleHolder extends RecyclerView.ViewHolder {

        ImageView remove;
        TextView name;
        TextView phone;

        public PeopleHolder(View itemView) {
            super(itemView);
            remove =  ButterKnife.findById(itemView,R.id.img_remove);
            name =  ButterKnife.findById(itemView,R.id.text_name);
            phone =  ButterKnife.findById(itemView,R.id.text_phone);

        }
    }
}
