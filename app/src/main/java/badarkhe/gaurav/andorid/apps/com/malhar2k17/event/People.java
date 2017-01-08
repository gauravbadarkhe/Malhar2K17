package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import java.util.ArrayList;

/**
 * Created by Admin on 30-10-2016.
 */
public class People {



    ArrayList<String> first_names =  new ArrayList<>();
    ArrayList<String> last_names =  new ArrayList<>();
    ArrayList<String> phones =  new ArrayList<>();
    long event_id =  0;
    long team_id = 0;
    long leader_id = 0;

    public long getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(long leader_id) {
        this.leader_id = leader_id;
    }

    public People(ArrayList<String> first_names, ArrayList<String> last_names, ArrayList<String> phones, long event_id,long leader_id) {
        this.first_names = first_names;
        this.last_names = last_names;
        this.phones = phones;
        this.event_id = event_id;
        this.leader_id = leader_id;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }

    public ArrayList<String> getFirst_names() {
        return first_names;
    }

    public void setFirst_names(ArrayList<String> first_names) {
        this.first_names = first_names;
    }

    public ArrayList<String> getLast_names() {
        return last_names;
    }

    public void setLast_names(ArrayList<String> last_names) {
        this.last_names = last_names;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }
}
