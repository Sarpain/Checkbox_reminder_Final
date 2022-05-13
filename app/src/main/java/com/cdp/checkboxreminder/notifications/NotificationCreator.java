package com.cdp.checkboxreminder.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationCreator extends Application {
    public static final String TITLE_ID = "Title";
    public static final String DESC_ID = "Desc";
    public static final String REPEAT_ID = "Repeat";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel Title = new NotificationChannel(
                    TITLE_ID,
                    "Title",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationChannel Desc = new NotificationChannel(
                    DESC_ID,
                    "Description",
                    NotificationManager.IMPORTANCE_LOW
            );

            NotificationChannel Repeat = new NotificationChannel(
                    REPEAT_ID,
                    "Repeating Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Title);
            manager.createNotificationChannel(Desc);
            manager.createNotificationChannel(Repeat);
        }
    }
}