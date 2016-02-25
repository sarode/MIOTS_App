package com.connect.connectapp;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // create the TabHost that will contain the Tabs
//        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
//        tabHost.setup();
//
//        TabSpec tab1 = tabHost.newTabSpec("First Tab");
//        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
//        TabSpec tab3 = tabHost.newTabSpec("Third tab");
//        TabSpec tab4 = tabHost.newTabSpec("Fourth tab");
//
//        // Set the Tab name and Activity
//        // that will be opened when particular Tab will be selected
//        tab1.setIndicator("Home");
//        tab1.setContent(new Intent(this, main_page.class));
//
//        tab2.setIndicator("Schedule");
//        tab2.setContent(new Intent(this, main_page.class));
//
//        tab3.setIndicator("Contacts");
//        tab3.setContent(new Intent(this, main_page.class));
//
//        tab4.setIndicator("Settings");
//        tab4.setContent(new Intent(this, main_page.class));
//
//        /** Add the tabs  to the TabHost to display. */
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);
//        tabHost.addTab(tab3);
//        tabHost.addTab(tab4);
    }
}
