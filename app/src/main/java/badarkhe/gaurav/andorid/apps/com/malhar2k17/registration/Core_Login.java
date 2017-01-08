package badarkhe.gaurav.andorid.apps.com.malhar2k17.registration;

import com.google.gson.Gson;

/**
 * Created by hubbelsoftware on 6/10/16.
 */
public class Core_Login {
    static String TAG = Core_Login.class.getSimpleName();
    public static String employeeID, OTP;
    static Gson gson = new Gson();

//    public static void login(final Context c) {
//        if (!new User().isLoggedIn(c)) {
//
//            CommonUtils.inputDialog(c, "Employee Login", "Please login to continue", "Request OTP", new MaterialDialog.InputCallback() {
//                @Override
//                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
//
//                    employeeID = input.toString();
//                }
//            }, new MaterialDialog.SingleButtonCallback() {
//                @Override
//                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    requestOTP(dialog.getInputEditText().getText().toString(), c);
//                    dialog.dismiss();
//
//                }
//            }).show();
//
//
//        }
//    }
//
//    public static void requestOTP(String employeeid, final Context c) {
//        final MaterialDialog progressd = CommonUtils.progressDialog(c, "Hey " + employeeid + "!!", "Wating for otp...").show();
//        WebUtils.getJsonData(new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d(TAG, "onResponse() returned: " + response.toString());
//                progressd.dismiss();
//                ZipdEmployee employee = gson.fromJson(response.toString(), ZipdEmployee.class);
//                verifyOTP(employee, c);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressd.dismiss();
//                Log.e(TAG, "onErrorResponse: ", error);
//            }
//        }, Api.Module1.requestOTP + employeeid);
//
//        Log.d(TAG, "requestOTP: " + Api.Module1.requestOTP + employeeid);
//
//    }
//
//    public static void verifyOTP(final ZipdEmployee employee, final Context c) {
//
//
//        if (employee.getId().equals("NA")) {
//
//            login(c);
//            CommonUtils.Toast("Invalid Employee ID", c);
//        } else {
//
//            CommonUtils.inputDialog(c, "Verify OTP", "OTP sent to " + CommonUtils.replaceLastChar(employee.getPhonenumber(), "*****", 5), "Verify OTP", new MaterialDialog.InputCallback() {
//                @Override
//                public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
//
//                    if (employee.getExtraData().equals(input.toString())) {
//                        welcomeEmployee(employee, c);
//                        dialog.dismiss();
//                    }
//                }
//            }, new MaterialDialog.SingleButtonCallback() {
//                @Override
//                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    if (employee.getExtraData().equals(dialog.getInputEditText().getText().toString())) {
//                        welcomeEmployee(employee, c);
//                        dialog.dismiss();
//                    } else {
//
//                        CommonUtils.Toast("Invalid OTP", c);
//                    }
//
//                }
//            }).show();
//        }
//
//    }
//
//    public static void welcomeEmployee(final ZipdEmployee employee, final Context c) {
//
//
//        CommonUtils.basicDialog(c,
//                "Welcome " + employee.getName(),
//                "Current Role : " + employee.getRole(),
//                "Proceed", new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        Details.addEmployee(employee, c);
//                        c.startActivity(new Intent(c, MainActivity.class));
//
//                    }
//                }).show();
//
//    }

}
