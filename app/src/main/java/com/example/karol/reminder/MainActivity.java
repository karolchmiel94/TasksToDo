package com.example.karol.reminder;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    Button addReminder;
    ListView reminderListView;
    AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addReminder = (Button) findViewById(R.id.button);
        reminderListView = (ListView) findViewById(R.id.reminderListView);
        reminderListView.setLongClickable(true);

        //Dialog showing up after long click on listview item
        reminderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                int pos = position+1;
                String selected = ((TextView)view.findViewById(R.id.title_row)).getText().toString();
                Toast.makeText(getApplicationContext(), "Id in database : " + id, Toast.LENGTH_LONG).show();
                //onItemClick(pos);
                onItemClick(selected);
                return true;
            }
        });

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("get_info");

        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AddReminderActivity.class);
                startActivity(intent);
            }
        });
    }


    //Dialog whereby we can choose whether to delete or edit data
    private void onItemClick(final String selected) {
        dialogBuilder = new AlertDialog.Builder(this);
        final String title = selected;
        dialogBuilder.setTitle("Hi, I'm dialog's title");
        dialogBuilder.setMessage("What do you want to do?");

        dialogBuilder.setNeutralButton("Set notification", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setNotification(selected);
            }
        });
        dialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Delete(title);
            }
        });
        dialogBuilder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Edit(title);
            }
        });
        dialogBuilder.show();
    }

    public void Delete(String title) {
        Toast.makeText(getApplicationContext(), "Deleting one row", Toast.LENGTH_SHORT).show();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("delete_item", title);
        refresh();
    }

    public void Edit(int position) {
        Toast.makeText(getApplicationContext(), "Editing " + position + ". row", Toast.LENGTH_SHORT).show();

    }

    private void refresh() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    //Notifiaction without setting date and time; Easy and quick notification. Working senseless, must be improved later
    public void setNotification(String text) {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        String title = text;
        Notification notification = new Notification.Builder(this)
                .setTicker("Ticker Title")
                .setWhen(System.currentTimeMillis()+5*1000)
                .setContentTitle(title)
                .setContentText("You better get your shit done")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).getNotification();
        notification.flags=Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0,notification);
    }

}


