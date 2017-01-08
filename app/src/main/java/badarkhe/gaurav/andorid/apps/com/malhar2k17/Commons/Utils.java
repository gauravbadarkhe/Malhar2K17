package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 11-10-2016.
 */
public class Utils {

    public static void Log(String msg, String tag) {
        Log.d(tag, msg);
    }

    public static class Notify{
        public static void toast(Context c,String msg){
            Toast.makeText(c,msg,Toast.LENGTH_SHORT).show();
        }
    }

    public static void launch(Context c,Class<?> cls,boolean finish){
        Intent intent = new Intent(c,cls);
        c.startActivity(intent);
        if (finish){
            ((Activity) c).finish();
        }
    }

    public static void launchLink(Context c,String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        c.startActivity(browserIntent);
    }



    public static void launch(Context c,Intent intent,boolean finish){

        c.startActivity(intent);
        if (finish){
            ((Activity) c).finish();
        }
    }

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static String ymwdhmsDifference(Date d1, Date d2)
    {
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long totalDiffDays = diff / (24 * 60 * 60 * 1000);
        long diffDays = totalDiffDays % 7;

        long diffWeeks = totalDiffDays/7;   // Full weeks are simply days / 7.
        diffDays = diffDays % 7;        // now we get the remaining days.


        String difference=
                 "W:"+Long.toString(diffWeeks)+
                "-D:"+Long.toString(diffDays)+
                "-H:"+String.format("%02d", diffHours)+
                "-M:"+String.format("%02d", diffMinutes)+
                "-S:"+String.format("%02d", diffSeconds);

        return difference;
    }

}
