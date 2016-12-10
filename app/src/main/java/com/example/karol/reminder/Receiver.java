package com.example.karol.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Karol on 23.11.2016.
 */

public class Receiver extends Service {

    //base to build notification. To do when asap
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Intent intent = new Intent(this, MainActivity.class);
        long[] pattern = {0, 300, 0};
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0123, intent, 0);
        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification title")
                .setContentText("Notification text")
                .setVibrate(pattern)
                .setAutoCancel(true);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0123, mBuilder.build());
    }
}
