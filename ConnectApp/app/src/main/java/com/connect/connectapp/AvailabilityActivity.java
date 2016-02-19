package com.connect.connectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class AvailabilityActivity extends AppCompatActivity {

    boolean[][] Availability = {
            {false, false, false},  // Monday
            {false, false, false},  // Tuesday
            {false, false, false},  // Wednesday
            {false, false, false},  // Thursday
            {false, false, false},  // Friday
            {false, false, false},  // Saturday
            {false, false, false}   // Sunday
    };

    int[][] AvailabilityIds = {
            {R.id.MondayMorning, R.id.MondayAfternoon, R.id.MondayEvening},
            {R.id.TuesdayMorning, R.id.TuesdayAfternoon, R.id.TuesdayEvening},
            {R.id.WednesdayMorning, R.id.WednesdayAfternoon, R.id.WednesdayEvening},
            {R.id.ThursdayMorning, R.id.ThursdayAfternoon, R.id.ThursdayEvening},
            {R.id.FridayMorning, R.id.FridayAfternoon, R.id.FridayEvening},
            {R.id.SaturdayMorning, R.id.SaturdayAfternoon, R.id.SaturdayEvening},
            {R.id.SundayMorning, R.id.SundayAfternoon, R.id.SundayEvening}
    };

    boolean[][] FakeAvailability = {
            {false, false, true},   // Monday
            {false, false, false},  // Tuesday
            {false, false, true},   // Wednesday
            {false, false, true},   // Thursday
            {false, false, false},  // Friday
            {false, true, false},   // Saturday
            {false, true, true}     // Sunday
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    // Called when the user clicks the Continue button
    public void continueAction(View view) {

        this.storeAvailability();

        // Move on to the availability screen
//        Intent intent = new Intent(this, AvailabilityActivity.class);
//        startActivity(intent);
    }

    //  Store off the indicated availability of the user.
    private void storeAvailability() {

        for (int i = 0; i < AvailabilityIds.length; i++) {
            for (int j = 0; j < AvailabilityIds[0].length; j++) {
                CheckBox checkbox = (CheckBox)findViewById(AvailabilityIds[i][j]);
                Availability[i][j] = checkbox.isChecked();
                Log.d("Availability", String.valueOf(Availability[i][j]));
            }
        }

    }

}
