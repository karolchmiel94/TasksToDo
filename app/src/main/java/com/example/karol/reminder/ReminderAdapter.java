package com.example.karol.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karol on 05.11.2016.
 */

public class ReminderAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ReminderAdapter(Context context, int resource) { super(context, resource); }

    public void add(Reminder object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReminderHolder reminderHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_reminder_row, parent, false);
            reminderHolder = new ReminderHolder();
            reminderHolder.tx_title = (TextView) row.findViewById(R.id.title_row);
            reminderHolder.tx_date = (TextView) row.findViewById(R.id.date_row);
            reminderHolder.tx_time = (TextView) row.findViewById(R.id.time_row);
            reminderHolder.tx_location = (TextView) row.findViewById(R.id.location_row);
            row.setTag(reminderHolder);
        }
        else {
            reminderHolder = (ReminderHolder) row.getTag();
        }

        Reminder reminder = (Reminder) getItem(position);
        reminderHolder.tx_title.setText(reminder.getTitle());
        reminderHolder.tx_date.setText(reminder.getDate());
        reminderHolder.tx_time.setText(reminder.getTime());
        reminderHolder.tx_location.setText(reminder.getLocation());

        return row;
    }

    static class ReminderHolder {
        TextView tx_title, tx_date, tx_time, tx_location;
    }
}
























