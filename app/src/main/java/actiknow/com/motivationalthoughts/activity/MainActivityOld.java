package actiknow.com.motivationalthoughts.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import actiknow.com.motivationalthoughts.R;
import actiknow.com.motivationalthoughts.utils.AppConfigTags;
import actiknow.com.motivationalthoughts.utils.AppConfigURL;
import actiknow.com.motivationalthoughts.utils.NetworkConnection;
import actiknow.com.motivationalthoughts.utils.Utils;


public class MainActivityOld extends AppCompatActivity {
    TextView tvQuotesDetails;
    CoordinatorLayout clMain;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        EnglishQuotesList();


    }


    private void initView() {
        tvQuotesDetails = (TextView) findViewById(R.id.tvQuotesDetails);
        clMain = (CoordinatorLayout)findViewById(R.id.clMain);
    }

    private void initData(){
        progressDialog = new ProgressDialog (this);


    }

    private void EnglishQuotesList () {
        if (NetworkConnection.isNetworkAvailable (MainActivityOld.this)) {
            Utils.showLog(Log.INFO, "" + AppConfigTags.URL, AppConfigURL.URL_QUOTES, true);
            Utils.showProgressDialog (progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            StringRequest strRequest1 = new StringRequest(Request.Method.GET, AppConfigURL.URL_QUOTES,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utils.showLog(Log.INFO,AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    boolean error = jsonObj.getBoolean(AppConfigTags.ERROR);
                                    String message = jsonObj.getString(AppConfigTags.MESSAGE);
                                    if (!error) {
                                        tvQuotesDetails.setText(new String (jsonObj.getString (AppConfigTags.QUOTES).getBytes ("ISO-8859-1"), "UTF-8"));
                                    } else {
                                        Utils.showSnackBar (MainActivityOld.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                    progressDialog.dismiss ();
                                } catch (Exception e) {
                                    progressDialog.dismiss ();
                                    Utils.showSnackBar (MainActivityOld.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace();

                                }
                            } else {
                                Utils.showSnackBar (MainActivityOld.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog(Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss ();
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss ();
                            Utils.showLog(Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString(), true);
                            Utils.showSnackBar (MainActivityOld.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    }) {
            };
            Utils.sendRequest(strRequest1, 60);
        } else {
            Utils.showSnackBar(this, clMain, getResources().getString(R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_go_to_settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialogIntent = new Intent(Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
            });
        }
    }
}
