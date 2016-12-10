package com.example.karol.reminder;

/**
 * Created by Karol on 05.11.2016.
 */

public class Reminder {

    private String title, date, time, location;

    public Reminder (String title, String date, String time, String location) {
        this.setTitle(title);
        this.setDate(date);
        this.setTime(time);
        this.setLocation(location);
    }

    //----------------------------------------------------------------------------------------------
    //                                      Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //----------------------------------------------------------------------------------------------
    //                                      Getters
    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public String getLocation() {
        return this.location;
    }

}








