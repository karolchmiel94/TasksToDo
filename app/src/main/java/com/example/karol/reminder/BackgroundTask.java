package com.example.karol.reminder;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Karol on 05.11.2016.
 */

public class BackgroundTask extends AsyncTask<String, Reminder, String> {

    Context ctx;
    ReminderAdapter reminderAdapter;
    Activity activity;
    ListView listView;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() { super.onPreExecute(); }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        DbOperations dbOperations = new DbOperations(ctx);

        if (method.equals("add_info")) {
            String title = params[1];
            String date = params[2];
            String time = params[3];
            String location = params[4];
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.addReminder(db, title, date, time, location);
            return "One row added !";
        }
        else if (method.equals("get_info")) {
            listView = (ListView) activity.findViewById(R.id.reminderListView);
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            Cursor cursor = dbOperations.getInformations(db);
            reminderAdapter = new ReminderAdapter(ctx, R.layout.display_reminder_row);
            String title, date, time, location, id;
            while (cursor.moveToNext()) {
                title = cursor.getString(cursor.getColumnIndex(ReminderContract.ReminderEntry.TITLE));
                date = cursor.getString(cursor.getColumnIndex(ReminderContract.ReminderEntry.DATE));
                time = cursor.getString(cursor.getColumnIndex(ReminderContract.ReminderEntry.TIME));
                location = cursor.getString(cursor.getColumnIndex(ReminderContract.ReminderEntry.LOCATION));
                Reminder reminder = new Reminder(title, date, time, location );
                publishProgress(reminder);
            }
            //Toast.makeText(ctx, "No reminders to show dude", Toast.LENGTH_LONG).show();
            return "get_info";
        }
        else if (method.equals("delete_item")) {
            String title = params[1];
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.deleteItem(db, title);
            return "delete_item";
        }
        else if (method.equals("item_info")) {

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Reminder... values) {
        reminderAdapter.add(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_info")) {
            listView.setAdapter(reminderAdapter);
        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}

















