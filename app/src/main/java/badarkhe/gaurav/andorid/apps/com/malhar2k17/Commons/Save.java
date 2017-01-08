package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


/**
 * Created by hubbelsoftware on 3/6/16.
 */
public class Save {

    public static String ME = "asjda8asdjdiuscitysada";
    public static String SESSION = "hubsidsdfdsfdsfhiiie";
    public static String phonenumber = "ghgjkbaf74bwkfifewn";
    public static String session_p = "sdajkdsdsajRMIDjkasn";
    public static String location = "sdalocationsdsajRMIDjkasn";
    public static String sublocation = "sdasublocationsdsajRMIDjkasn";
    public static String orderpriority = "sdaorderpriorityjRMIDjkasn";
    public static String ordertype = "sdaoasdaordertypeypsdseasn";
    public static String placedby_type = "sdaordordertypsdseasn";
    public static String name = "sdaordonamerdertypeasn";
    public static String keyM = "sdaordonkeyMamdwerdertypeasn";
    public static String keyM_valid = "sdaordonkeyM_validkeyMamerdertypeasn";
    public static String keyM_profile = "sdaordokeyM_profilenkeyMamerdertypeasn";
    public static String employee_email = "sdemplee_emailaordordertypsdssn";
    public static String employee_name = "sdaordorsdse33ployee_maeeasn";
    public static String employee_phonenumber = "sdempee_phonenumberaordypsdseasn";
    public static String employee_role = "sdaordordemployee_roletypsdseasn";
    public static String employee_id = "sdaordoremployee_iddertypsn";
    public static String basicPricing = "basic_pricing";





    public static void putSharedPreferencesInt(Context context, String key,
                                               int value) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void putSharedPreferencesBoolean(Context context, String key,
                                                   boolean val) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putBoolean(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesString(Context context, String key,
                                                  String val) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putString(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesFloat(Context context, String key,
                                                 float val) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putFloat(key, val);
        edit.commit();
    }

    public static void putSharedPreferencesLong(Context context, String key,
                                                long val) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor edit = preferences.edit();
        edit.putLong(key, val);
        edit.commit();
    }

    public static long getSharedPreferencesLong(Context context, String key,
                                                long _default) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getLong(key, _default);
    }

    public static float getSharedPreferencesFloat(Context context, String key,
                                                  float _default) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getFloat(key, _default);
    }

    public static String getSharedPreferencesString(Context context,
                                                    String key, String _default) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getString(key, _default);
    }

    public static int getSharedPreferencesInt(Context context, String key,
                                              int _default) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getInt(key, _default);
    }

    public static boolean getSharedPreferencesBoolean(Context context,
                                                      String key, boolean _default) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, _default);
    }



}
