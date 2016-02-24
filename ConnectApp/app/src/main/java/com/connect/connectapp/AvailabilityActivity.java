package com.connect.connectapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;


import java.util.Calendar;
import java.util.logging.Logger;

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
    }

    // Called when the user clicks the Continue button
    public void continueAction(View view) {

        this.storeAvailability();
        this.testStorage();
        this.calculateFirstCall();

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

    //  Store off the indicated availability of the user.
    private void calculateFirstCall() {

        SharedPreferences pref = getSharedPreferences("Availability", MODE_PRIVATE);

        int current_day_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        Log.d("current day of week:", String.valueOf(current_day_of_week));

        String catchup_day = DaysOfWeek[6];     // Sunday
        String catchup_time = TimesOfDay[2];    // Evening

        outerloop:
        for (int i = 0; i < DaysOfWeek.length; i++) {
            for (int j = 0; j < TimesOfDay.length; j++) {
                boolean MyAvailability = pref.getBoolean("Available" + DaysOfWeek[i] + TimesOfDay[j], false);
                boolean HerAvailability = FakeAvailability[i][j];

                if (MyAvailability == true && HerAvailability == true) {
                    catchup_day = DaysOfWeek[i];
                    switch (TimesOfDay[j]) {
                        case "Morning":
                            catchup_time = "10 am";
                            break;
                        case "Afternoon":
                            catchup_time = "2 pm";
                            break;
                        case "Evening":
                            catchup_time = "6 pm";
                            break;
                        default:
                            catchup_time = "6 pm";
                    }
                    break outerloop;
                }
            }
        }

        Calendar catchup_date = this.getNextDateForDayOfWeek(catchup_day);
        this.startConfirmationActivity(catchup_date, catchup_time);
    }

    private static String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    // get the date of the next Wednesday, for example
    private Calendar getNextDateForDayOfWeek(String day_of_week) {

        int dow = this.convertToCalendarDay(day_of_week);

        // get the current date
        Calendar date = Calendar.getInstance();

        // get the number of days between the day in question and the current day
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);

        // if the day has already passed this week, then use next week's date
        if (!(diff >0)) {
            diff +=7;
        }

        // add the difference to the current date to get the desired date
        date.add(Calendar.DAY_OF_MONTH, diff);

        return date;

    }

    private int convertToCalendarDay(String day_of_week) {
        if (day_of_week == "Monday") {
            return Calendar.MONDAY; // 2
        } else if (day_of_week == "Tuesday") {
            return Calendar.TUESDAY; // 3
        } else if (day_of_week == "Wednesday") {
            return Calendar.WEDNESDAY; // 4
        } else if (day_of_week == "Thursday") {
            return Calendar.THURSDAY; // 5
        } else if (day_of_week == "Friday") {
            return Calendar.FRIDAY; // 6
        } else if (day_of_week == "Saturday") {
            return Calendar.SATURDAY; // 7
        } else if (day_of_week == "Sunday") {
            return Calendar.SUNDAY; // 1
        } else {
            Log.d("","Not a valid day of week");
        }

        return Calendar.SUNDAY;
    }

    private String convertToDayOfWeek(int calendar_day) {
        if (calendar_day == Calendar.MONDAY) {
            return "Monday"; // 2
        } else if (calendar_day == Calendar.TUESDAY) {
            return "Tuesday"; // 3
        } else if (calendar_day == Calendar.WEDNESDAY) {
            return "Wednesday"; // 4
        } else if (calendar_day == Calendar.THURSDAY) {
            return "Thursday"; // 5
        } else if (calendar_day == Calendar.FRIDAY) {
            return "Friday"; // 6
        } else if (calendar_day == Calendar.SATURDAY) {
            return "Saturday"; // 7
        } else if (calendar_day == Calendar.SUNDAY) {
            return "Sunday"; // 1
        } else {
            Log.d("","Not a valid day of week");
        }

        return "Sunday";
    }

    //  Start the new activity with the call date and time
    private void startConfirmationActivity(Calendar catchup_date, String catchup_time) {

        String catchup_day_of_week = this.convertToDayOfWeek(catchup_date.get(Calendar.DAY_OF_WEEK));
        String month = this.theMonth(catchup_date.get(Calendar.MONTH));
        int catchup_date_of_month = catchup_date.get(Calendar.DAY_OF_MONTH);

        // Move on to the confirmation screen
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("CATCHUP_DAY_OF_WEEK", catchup_day_of_week);
        intent.putExtra("CATCHUP_MONTH", month);
        intent.putExtra("CATCHUP_DATE_OF_MONTH", catchup_date_of_month);
        intent.putExtra("CATCHUP_TIME", catchup_time);
        startActivity(intent);

    }

}
