package com.connect.connectapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    //** Called when the user clicks the Continue button */
    public void continueAction(View view) {
        // Move on to the availability screen
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}
