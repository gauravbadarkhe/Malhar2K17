package badarkhe.gaurav.andorid.apps.com.malhar2k17.registration;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons.Save;

public class User {

	public String first_name = "";
	public String last_name = "";
	public String name = null;
	public String college = null;
	public String branch = null;
	public String semester = null;
	public String section = null;
	public String phonenumber = null;
	public long uid = 0;
	public String loginToken = null;
	
//	public static boolean autorise(String login_token){
//		return Core_Utils.autorise(login_token);
//	}
//
//	/**
//	 * @param userid
//	 * @return {@link User}
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	public User get(long userid) throws ClassNotFoundException, SQLException {
//		ResultSet rs = Core_Query.queryResultset(Core_SQL.User
//				.getuserbyID(userid));
//		while (rs.next()) {
//			name = rs.getString("name");
//			college = rs.getString("college");
//			branch = rs.getString("branch");
//			semester = rs.getString("semester");
//			section = rs.getString("section");
//			phonenumber = rs.getString("phonenumber");
//			email = rs.getString("email");
//			uid = rs.getLong("uid");
//		}
//		rs.close();
//		return this;
//	}

	public  User getUser(Context c){
		return  new Gson().fromJson(Save.getSharedPreferencesString(c,Save.ME,toString()),User.class);
	}

	public String toString() {
		return new Gson().toJson(this).toString();
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public static boolean isLoggedIn(Context context){
		return Save.getSharedPreferencesBoolean(context,Save.SESSION,false);
	}
	public static void login(Context c,boolean loggedin){
		Save.putSharedPreferencesBoolean(c,Save.SESSION,loggedin);
	}

	public static boolean isnumbersaved(Context context){
		return Save.getSharedPreferencesBoolean(context,Save.session_p,false);
	}
	public static void saveNumber(Context c,boolean loggedin){
		Save.putSharedPreferencesBoolean(c,Save.session_p,loggedin);
	}


}
