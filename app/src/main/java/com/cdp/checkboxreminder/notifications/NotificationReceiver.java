package com.cdp.checkboxreminder.notifications;

import static com.cdp.checkboxreminder.notifications.NotificationCreator.REPEAT_ID;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.activity.MainActivity;
import com.cdp.checkboxreminder.managers.DataManager;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Notification notification = new NotificationCompat.Builder(context, REPEAT_ID)

                .setSmallIcon(R.drawable.ic_baseline_short_text_24)
                .setContentTitle("Tienes "+String.valueOf(DataManager.todayTasks+" tarea/s por hacer."))
                .setContentText("Entra para ver mas.")
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(3, notification);
    }
}