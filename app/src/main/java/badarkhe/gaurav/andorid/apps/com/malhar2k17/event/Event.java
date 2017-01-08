package badarkhe.gaurav.andorid.apps.com.malhar2k17.event;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event {

	public long event_id = 0;
	public String event_name = null;
	public String description = null;
	boolean isregistered = false;
	public String message = null;
	public long date_time = 0;
	public long registration_date_time = 0;
	public int registration_count = 0;
	public String orginiser = null;
	public String photo_base_url = null;
	public int photo_count = 0;
	public int registration_type = 2;

	public List<String> images = new ArrayList<>();

	public boolean isregistered() {
		return isregistered;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getRegistration_type() {
		return registration_type;
	}

	public void setRegistration_type(int registration_type) {
		this.registration_type = registration_type;
	}

	public long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIsregistered() {
		return isregistered;
	}

	public void setIsregistered(boolean isregistered) {
		this.isregistered = isregistered;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getDate_time() {
		return date_time;
	}

	public void setDate_time(long date_time) {
		this.date_time = date_time;
	}

	public long getRegistration_date_time() {
		return registration_date_time;
	}

	public void setRegistration_date_time(long registration_date_time) {
		this.registration_date_time = registration_date_time;
	}

	public int getRegistration_count() {
		return registration_count;
	}

	public void setRegistration_count(int registration_count) {
		this.registration_count = registration_count;
	}

	public String getOrginiser() {
		return orginiser;
	}

	public void setOrginiser(String orginiser) {
		this.orginiser = orginiser;
	}

	public String getPhoto_base_url() {
		return photo_base_url;
	}

	public void setPhoto_base_url(String photo_base_url) {
		this.photo_base_url = photo_base_url;
	}

	public int getPhoto_count() {
		return photo_count;
	}

	public void setPhoto_count(int photo_count) {
		this.photo_count = photo_count;
	}

	

}
