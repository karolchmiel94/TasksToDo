package com.example.karol.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Karol on 05.11.2016.
 */

public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "reminders.db";
    public static final String COLUMN_ID = "_id";
    private static final String CREATE_QUERY = "CREATE TABLE " + ReminderContract.ReminderEntry.TABLE_NAME + " (" +
            ReminderContract.ReminderEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ReminderContract.ReminderEntry.TITLE + " TEXT, " +
            ReminderContract.ReminderEntry.DATE + " TEXT, " +
            ReminderContract.ReminderEntry.TIME + " TEXT, " +
            ReminderContract.ReminderEntry.LOCATION + " TEXT );";

    DbOperations (Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
        Log.d("Database operations", "Database created !");
    };


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created !");
    }

    public void addReminder(SQLiteDatabase db, String title, String date, String time, String location) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ReminderContract.ReminderEntry.TITLE, title);
        contentValues.put(ReminderContract.ReminderEntry.DATE, date);
        contentValues.put(ReminderContract.ReminderEntry.TIME, time);
        contentValues.put(ReminderContract.ReminderEntry.LOCATION, location);
        db.insert(ReminderContract.ReminderEntry.TABLE_NAME, null, contentValues);
        Log.d("Database operations", "One row inserted !");
    }

    public Cursor getInformations(SQLiteDatabase db) {
        String[] projections = {ReminderContract.ReminderEntry.TITLE, ReminderContract.ReminderEntry.DATE,
                                ReminderContract.ReminderEntry.TIME, ReminderContract.ReminderEntry.LOCATION};
        Cursor cursor = db.query(ReminderContract.ReminderEntry.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    //                      TO DO !!!
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void deleteItem(SQLiteDatabase db, String title) {
        db.execSQL("DELETE FROM reminders_table WHERE " + ReminderContract.ReminderEntry.TITLE + " = '" + title + "'");
    }


}
