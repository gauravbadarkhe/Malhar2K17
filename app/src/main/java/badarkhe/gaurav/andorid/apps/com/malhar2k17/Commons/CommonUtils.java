package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.List;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.event.RegisterEvent;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.mrapp.android.bottomsheet.BottomSheet;

/**
 * Created by hubbelsoftware on 3/6/16.
 */
public class CommonUtils {
    public static Gson gson = new Gson();
    public static DecimalFormat df;

    public static void Toast(String s, Context c) {
        Toast.makeText(c, s, Toast.LENGTH_LONG).show();
    }

    public static void Toast(String s, Context c, boolean istest) {
        Toast.makeText(c, s, Toast.LENGTH_LONG).show();
    }

    public static <T> List<T> toList(String json,  Class<?> clazz) {
        if (null == json) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json,new TypeToken<List<RegisterEvent>>() {}.getType());
    }

    public static BottomSheet makeSheet(List<RegisterEvent> registerEvents, Context context) {
        BottomSheet.Builder bottomSheet = new BottomSheet.Builder(context);
        bottomSheet.setTitle("Register for-");
        int i = 0;

        for (RegisterEvent r : registerEvents
                ) {
            bottomSheet.addItem(i, r.getIsactive() ? r.getName() : r.getName() + " (Disabled)").setItemColor(r.getIsactive() ? Color.BLACK : Color.GRAY);
            i++;
        }
        return bottomSheet.create();
    }

    public static String replaceLastChar(String s, String replace, int repace_length) {
        int length = s.length();
        //Check whether or not the string contains at least four characters; if not, this method is useless
        if (length < repace_length)
            return "Error: The provided string is not greater than four characters long.";
        return s.substring(0, length - repace_length) + replace;
    }

    public static double getPercentage(Double num, Double amount) {


        return num * amount / 100;

    }

    public static void Log(String s, String tag) {
        Log.e(tag, s);
    }

    public static Object fromJson(String json, Class<?> cls) {
        return gson.fromJson(json, cls);
    }


    public static List<?> getPricingFromJsonArray(String data) {
        Type listType = new TypeToken<List<?>>() {
        }.getType();
        List<?> yourList = new Gson().fromJson(data, listType);
        return yourList;
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static class Validate {
        public static boolean Phonenumber(String text) {


            return android.util.Patterns.PHONE.matcher(text).matches();
        }

        public static boolean String(String text) {


            return text.equals(null);
        }
    }

    public static double str2Double(String s) {
        return Double.valueOf(s);
    }
//
//
//
//    public static MaterialDialog.Builder basicDialog(Context c, String tittle, String content, String btntext_positive, MaterialDialog.SingleButtonCallback positive){
//
//
//        return    new MaterialDialog.Builder(c)
//                .title(tittle)
//                .content(content)
//                .positiveText(btntext_positive)
//                .cancelable(false)
//                .onPositive(positive);
//
//
//
//
//
//    }
//
//    public static MaterialDialog.Builder basicDialog(Context c, String tittle, String content, String btntext_positive,String btntext_negative,  MaterialDialog.SingleButtonCallback positive,MaterialDialog.SingleButtonCallback negative){
//
//
//        return    new MaterialDialog.Builder(c)
//                .title(tittle)
//                .content(content)
//                .positiveText(btntext_positive)
//                .negativeText(btntext_negative)
//                .cancelable(false)
//                .onPositive(positive)
//                .onNegative(negative);
//
//
//
//
//    }
//
//    public static MaterialDialog.Builder progressDialog(Context c,String tittle,String content){
//     return    new MaterialDialog.Builder(c)
//                .title(tittle)
//                .content(content)
//                .progress(true, 0);
//    }
//
//    public static MaterialDialog.Builder inputDialog(Context c, String tittle, String content, String btntext, MaterialDialog.InputCallback input, MaterialDialog.SingleButtonCallback positive){
//
//
//        return new MaterialDialog.Builder(c)
//                .title(tittle)
//                .content(content)
//                .inputType(InputType.TYPE_CLASS_TEXT)
//                .input("Employee ID", "", input)
//                .cancelable(false)
//                .autoDismiss(false)
//                .positiveText(btntext)
//                .onPositive(positive);
//
//
//    }

    public static void showAlert(String message, Context context, DialogInterface.OnClickListener positiveclick, String positivetext, DialogInterface.OnClickListener negativeclick, String negativetext) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton(positivetext, positiveclick)
                .setNegativeButton(negativetext, negativeclick)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void showAlert(String message, Context context, DialogInterface.OnClickListener positiveclick, String positivetext) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positivetext, positiveclick)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public static Snackbar bar(final View coordinatorLayout, String message, String action, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction(action, clickListener);

        return snackbar;
    }

    public static void bar(final View coordinatorLayout, String message, @Nullable int duration) {
        if (duration == 0) {
            Snackbar snackbar1 = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
            snackbar1.show();
        } else {
            Snackbar snackbar1 = Snackbar.make(coordinatorLayout, message, duration);
            snackbar1.show();
        }


    }

    public static void bar(final View coordinatorLayout, String message, @Nullable int duration, View.OnClickListener clickListener, String action) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, duration)
                .setAction(action, clickListener);


    }


    public static class Dialogs {


        public static SweetAlertDialog showDialog(Context c, String Tittle, String msg, String conformtext, int type, SweetAlertDialog.OnSweetClickListener onClickListener) {

            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(c, type);
            sweetAlertDialog.setTitleText(Tittle);
            sweetAlertDialog.setContentText(msg);
            sweetAlertDialog.setConfirmText(conformtext);
            sweetAlertDialog.showCancelButton(true);
            sweetAlertDialog.setCancelText("Cancel");

            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.setConfirmClickListener(onClickListener);
            return sweetAlertDialog;

        }

        public static SweetAlertDialog showProgressDialog(Context c, String msg) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(c, SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.setTitleText(msg);

            sweetAlertDialog.showCancelButton(false);
            sweetAlertDialog.setCancelable(false);
            return sweetAlertDialog;

        }


        public static AlertDialog.Builder listDialog(String tittle, Context c, int Res_layout, String[] data
                , DialogInterface.OnClickListener negative, String negative_text,
                                                     DialogInterface.OnClickListener positive, String positive_text,
                                                     DialogInterface.OnClickListener listclick) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(c);
            builderSingle.setIcon(android.R.drawable.ic_dialog_info);
            builderSingle.setTitle(tittle);

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    c,
                    Res_layout, data);


            builderSingle.setNegativeButton(
                    negative_text, negative);
            builderSingle.setPositiveButton(
                    positive_text, positive);

            builderSingle.setAdapter(
                    arrayAdapter,
                    listclick);
            return builderSingle;

        }

        public ProgressDialog progressDialog(Context c) {
            ProgressDialog progressDialog = new ProgressDialog(c);
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            return progressDialog;
        }

    }


    public static double roundDecimal(double value, String decimalformat) {

        df = new DecimalFormat(decimalformat);

        return Double.valueOf(df.format(value));

    }


}
