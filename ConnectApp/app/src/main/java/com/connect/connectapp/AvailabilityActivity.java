package com.connect.connectapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class AvailabilityActivity extends AppCompatActivity {

    String[] DaysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] TimesOfDay = {"Morning", "Afternoon", "Evening"};

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
        this.testStorage();

        // Move on to the confirmation screen
        Intent intent = new Intent(this, ConfirmationActivity.class);
        startActivity(intent);
    }

    //  Store off the indicated availability of the user.
    private void storeAvailability() {

        SharedPreferences pref = getSharedPreferences("Availability", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        for (int i = 0; i < AvailabilityIds.length; i++) {
            for (int j = 0; j < AvailabilityIds[0].length; j++) {

                // Get the availability from the UI
                CheckBox checkbox = (CheckBox)findViewById(AvailabilityIds[i][j]);
                Availability[i][j] = checkbox.isChecked();
                //Log.d("Availability", String.valueOf(Availability[i][j]));

                // Save it in shared preferences
                editor.putBoolean("Available" + DaysOfWeek[i] + TimesOfDay[j], Availability[i][j]);
            }
        }

        editor.commit();

    }

    // Read availability from shared preferences and print it to the console
    private void testStorage() {

        SharedPreferences pref = getSharedPreferences("Availability", MODE_PRIVATE);

        for (int i = 0; i < AvailabilityIds.length; i++) {
            for (int j = 0; j < AvailabilityIds[0].length; j++) {

                // Read availability from shared preferences
                boolean availability = pref.getBoolean("Available" + DaysOfWeek[i] + TimesOfDay[j],false);
                Log.d("Available" + DaysOfWeek[i] + TimesOfDay[j], String.valueOf(availability));
            }
        }
    }

}
