package com.connect.connectapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    //public static TextView your_first_call_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String catchup_day_of_week = extras.getString("CATCHUP_DAY_OF_WEEK");
            String month = extras.getString("CATCHUP_MONTH");
            int catchup_date_of_month = extras.getInt("CATCHUP_DATE_OF_MONTH");
            String catchup_time = extras.getString("CATCHUP_TIME");
            TextView your_first_call_obj = (TextView)findViewById(R.id.your_first_call);
            your_first_call_obj.setText("Congratulations, your first catchup is scheduled for "
                    + catchup_day_of_week
                    + ", "
                    + month + " "
                    + String.valueOf(catchup_date_of_month)
                    + " at "
                    + catchup_time
                    + ".");
        }
    }

}
