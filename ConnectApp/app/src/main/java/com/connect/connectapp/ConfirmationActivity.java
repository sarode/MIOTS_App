package com.connect.connectapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
            String catchup_date_and_time = extras.getString("CATCHUP_DAY_AND_TIME");
            TextView your_first_call_obj = (TextView)findViewById(R.id.your_first_call);
            your_first_call_obj.setText("Great, your first call is scheduled for " + catchup_date_and_time);
        }
    }

}
