package badarkhe.gaurav.andorid.apps.com.malhar2k17.home;

/**
 * Created by Admin on 23-12-2016.
 */

public class MalharNotification {
    long id = 0;
    long time = 0;
    int type = 0;
    String tittle = null;
    String body = null;
    String linkUri = null;
    String imageUri = null;

    public MalharNotification() {
        super();
    }

    public MalharNotification(long id, long time, int type, String tittle,
                              String body, String linkUri, String imageUri) {
        super();
        this.id = id;
        this.time = time;
        this.type = type;
        this.tittle = tittle;
        this.body = body;
        this.linkUri = linkUri;
        this.imageUri = imageUri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLinkUri() {
        return linkUri;
    }

    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

}
