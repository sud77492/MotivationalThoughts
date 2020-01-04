package actiknow.com.motivationalthoughts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import actiknow.com.motivationalthoughts.R;
import actiknow.com.motivationalthoughts.utils.AppConfigTags;

/**
 * Created by actiknow on 7/5/17.
 */

public class QuotesDetailsActivity extends AppCompatActivity {
    TextView tvQuotesDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_details);
        initView();
        initData();

    }

    private void initView() {
        tvQuotesDetails = (TextView) findViewById(R.id.tvQuotesDetails);
    }

    private void initData(){
        Intent intent = getIntent();
        String quotes = intent.getExtras().getString(AppConfigTags.QUOTES);
        tvQuotesDetails.setText(quotes);
    }

}
