package actiknow.com.motivationalthoughts.service;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Map;

import actiknow.com.motivationalthoughts.utils.AppConfigTags;
import actiknow.com.motivationalthoughts.utils.AppConfigURL;
import actiknow.com.motivationalthoughts.utils.NetworkConnection;
import actiknow.com.motivationalthoughts.utils.QuotesEnglishPref;
import actiknow.com.motivationalthoughts.utils.Utils;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName ();
    @Override
    public void onTokenRefresh () {
        super.onTokenRefresh ();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Refreshed Token",""+refreshedToken);
        QuotesEnglishPref quotesEnglishPref = QuotesEnglishPref.getInstance ();
        quotesEnglishPref.putStringPref (getApplicationContext (), QuotesEnglishPref.FIREBASE_ID, refreshedToken);
        InsertFirebaseIdToServer(refreshedToken);

   }

    private void InsertFirebaseIdToServer(final String refreshedToken){
        if (NetworkConnection.isNetworkAvailable (getApplicationContext())) {
            Utils.showLog(Log.INFO, "" + AppConfigTags.URL, AppConfigURL.URL_FIREBASE2, true);
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put(AppConfigTags.FIREBASE_ID, refreshedToken);
                final String mRequestBody = jsonBody.toString();
                Log.e("JSON", mRequestBody);
                StringRequest strRequest1 = new StringRequest(Request.Method.POST, AppConfigURL.URL_FIREBASE2,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Utils.showLog(Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                                if (response != null) {
                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
//                                    boolean error = jsonObj.getBoolean(AppConfigTags.ERROR);
//                                    String message = jsonObj.getString(AppConfigTags.MESSAGE);

                                        // Utils.showSnackBar (MainActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                        Toast.makeText(getApplicationContext(),jsonObj.getString("data"),Toast.LENGTH_LONG).show();

                                    } catch (Exception e) {
                                        //  Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                        Toast.makeText(getApplicationContext(),"Exception Error",Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                } else {
                                    // Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    Utils.showLog(Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                                }
                            }
                        },
                        new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Utils.showLog(Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString(), true);
                            }
                        }) /*{
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();
                    params.put (AppConfigTags.FIREBASE_ID, refreshedToken);
                    Utils.showLog(Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }*/
                {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);

                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                /*@Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog(Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }*/
                };
                Utils.sendRequest(strRequest1, 60);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }
}

