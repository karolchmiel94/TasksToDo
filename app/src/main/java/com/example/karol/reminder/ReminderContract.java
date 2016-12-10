package com.example.karol.reminder;

/**
 * Created by Karol on 05.11.2016.
 */

public class ReminderContract {

    ReminderContract() {}

    public static abstract class ReminderEntry {
        public static final String TABLE_NAME = "reminders_table";
        public static final String COLUMN_ID = "_id";
        public static final String TITLE = "title";
        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String LOCATION = "location";
    }
}
