package com.example.karol.reminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddReminderActivity extends AppCompatActivity {

    private static final int SEVEN_DAYS = 604800000;
    private static String timeString, dateString;
    private Date mDate;
    static final int PICK_LOCATION_REQUEST = 1;

    private static TextView titleTextView, dateTextView, timeTextView, locationTextView;
    private static TextView setLocation, dateEditText, timeEditText;
    private EditText titleEditText;
    private Button addButton, clearButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        setLocation = (TextView) findViewById(R.id.setLocation);
        dateEditText = (TextView) findViewById(R.id.dateEditText);
        timeEditText = (TextView) findViewById(R.id.timeEditText);

        titleEditText =(EditText) findViewById(R.id.titleEditText);

        addButton = (Button) findViewById(R.id.addButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        setDefaultDateTime();

        //          Adding date
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Setting date", Toast.LENGTH_SHORT);
                showDatePickerDialog();
            }
        });

        //          Ading time
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        //          Adding localization
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // My API KEY AIzaSyCCgX8Q5O6kgTZQqhXAgdoPiRRJnwY8vIk
                //Intent intent = new Intent(AddReminderActivity.this, MapsActivity.class);
                //startActivity(intent);

                //New, better intent
                Intent intent = new Intent(AddReminderActivity.this, MapsActivity.class);
                startActivityForResult(intent, PICK_LOCATION_REQUEST);
            }
        });

        //          Action while using ADD button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent refresh = new Intent(AddReminderActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });

        //          Action while using CLEAR button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleEditText.setText("");
                setDefaultDateTime();
            }
        });

        //          Action while using CANCEL button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });
    }
    //          Saving data to database
    public void saveData() {
        String title = titleEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String location = setLocation.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("add_info", title, date, time, location);
    }

    //          Method to set date and time to default
    private void setDefaultDateTime() {
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);

        Calendar c = Calendar.getInstance();
        c.setTime(mDate);

        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dateEditText.setText(dateString);

        setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.MILLISECOND));
        timeEditText.setText(timeString);
    }

    //          Setting actual date
    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10) mon = "0" + monthOfYear;
        if (dayOfMonth < 10) day = "0" + dayOfMonth;

        dateString = year + "-" + mon + "-" + day;
        //Toast.makeText(AddReminderActivity.this, dateString, Toast.LENGTH_SHORT).show();
    }

    //          Setting actual time
    private static void setTimeString(int hourOfDay, int minute, int mili) {
        String hour = "" + hourOfDay;
        String min = "" + minute;

        if (hourOfDay < 10) hour = "0" + hourOfDay;
        if (minute < 10) min = "0" + minute;

        timeString = hour + ":" + min + ":00";
    }

    private void showDatePickerDialog() {
        Calendar mCurrentDate = Calendar.getInstance();
        int mYear = mCurrentDate.get(Calendar.YEAR);
        int mMonth = mCurrentDate.get(Calendar.MONTH);
        int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(AddReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                selectedmonth = selectedmonth + 1;
                String day, month;
                if (selectedday < 10) day = "0" + selectedday; else day = "" + selectedday;
                if (selectedmonth < 10) month = "0" + selectedmonth; else month = "" + selectedmonth;
                String date = "" + selectedyear + "-" + month + "-" + day;
                dateEditText.setText(date);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    } //Done

    private void showTimePickerDialog() {
        //Próba ogarnięcia tematu...
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String hour = "" + selectedHour;
                String minute = "" + selectedMinute;
                if (selectedHour < 10) hour = "0" + selectedHour;
                if (selectedMinute < 10) minute = "0" + selectedMinute;
                timeEditText.setText( hour + ":" + minute + ":00");
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
        //... i jej koniec
        Toast.makeText(getApplicationContext(), "TimePicker dialog showing here soon!", Toast.LENGTH_SHORT).show();
    } //Done

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                setLocation.setText(result);
            }
            if(resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Got no location data", Toast.LENGTH_LONG).show();
            }
        }
    }
}