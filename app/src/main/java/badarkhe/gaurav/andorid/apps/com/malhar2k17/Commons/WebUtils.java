package badarkhe.gaurav.andorid.apps.com.malhar2k17.Commons;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import badarkhe.gaurav.andorid.apps.com.malhar2k17.SampleApplication;

/**
 * Created by hubbelsoftware on 3/6/16.
 */
public class WebUtils {
    private static String tag = "WebUtils:";
    //public static String base_domain = "http://192.168.0.103:8080/2k17/";
    //public static String base_domain = "http://zipd.in:8081/2k17/";
    public static String base_domain = "http://zipd.in:8081/2k17_base/";

    public static JSONArray jsonArrayRequest(final String url, Context c) {
        final JSONArray[] jsonArray = {new JSONArray()};
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                jsonArray[0] = response;
                Utils.Log(response.toString(), tag + url);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.Log(error.getMessage().toString(), tag + url);
                jsonArray[0] = new JSONArray();

            }
        });
        SampleApplication.getInstance().addToRequestQueue(jsonArrayRequest);
        return jsonArray[0];
    }

    public static void postJsonData(Response.Listener<JSONObject> listener,
                                    Response.ErrorListener errlsn, String url, String requestBody) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, listener, errlsn);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }

    public static void postJsonData(Response.Listener<JSONObject> listener,
                                    Response.ErrorListener errlsn, String url, String requestBody, Context c) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, listener, errlsn);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(objectRequest);
        requestQueue.start();
    }

    public static void postJsonData2(Response.Listener<JSONObject> listener,
                                     Response.ErrorListener errlsn, String url, String requestBody, Context c) {

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, listener, errlsn);


        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }


    public static void postJsonData2(Response.Listener<JSONObject> listener,
                                     Response.ErrorListener errlsn, String url, JSONObject requestBody, Context c) {

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, listener, errlsn);


        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //SampleApplication.getInstance().addToRequestQueue(objectRequest);


        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(objectRequest);
        requestQueue.start();

    }

    public static void postJsonArrayData(Response.Listener<JSONArray> listener,
                                         Response.ErrorListener errlsn, String url, String requestBody) {
        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.POST, url, requestBody, listener, errlsn);
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }

    public static void postStringData(Response.Listener<String> listener,
                                      Response.ErrorListener errlsn, String url) {
        StringRequest objectRequest = new StringRequest(Request.Method.POST, url, listener, errlsn);
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }

    public static void getStringData(Response.Listener<String> listener,
                                     Response.ErrorListener errlsn, String url) {
        StringRequest objectRequest = new StringRequest(Request.Method.GET, url, listener, errlsn);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }


    public static void getJsonData(Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errlsn, String url) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(url, listener, errlsn);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }

    public static void getJsonArrayData(Response.Listener<JSONArray> listener,
                                        Response.ErrorListener errlsn, String url) {
        JsonArrayRequest objectRequest = new JsonArrayRequest(url.replace(" ", "%20"), listener, errlsn);
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        SampleApplication.getInstance().addToRequestQueue(objectRequest);
    }


}
