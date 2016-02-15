package com.connect.connectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }

    //** Called when the user clicks the Continue button */
    public void continueAction(View view) {
        // Move on to the availability screen
        Intent intent = new Intent(this, AvailabilityActivity.class);
        startActivity(intent);
    }
}
