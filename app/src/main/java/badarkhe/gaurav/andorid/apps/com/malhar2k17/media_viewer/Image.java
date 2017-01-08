package badarkhe.gaurav.andorid.apps.com.malhar2k17.media_viewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lincoln on 04/04/16.
 */
public class Image implements Serializable{
    List<String> images =  new ArrayList<>();
    List<Integer> images_base =  new ArrayList<>();
    String event_name =  null;
    String event_desc =  null;

    public Image( List<String> images,List<Integer> images_base, String event_name, String event_desc) {
        this.images_base = images_base;
        this.event_name = event_name;
        this.event_desc = event_desc;
    }

    public Image(List<String> images, String event_name, String event_desc) {
        this.images = images;
        this.event_name = event_name;
        this.event_desc = event_desc;
    }


    public List<Integer> getImages_base() {
        return images_base;
    }

    public void setImages_base(List<Integer> images_base) {
        this.images_base = images_base;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
