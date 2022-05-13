package com.cdp.checkboxreminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.cdp.checkboxreminder.notifications.NotificationReceiver;
import com.cdp.checkboxreminder.managers.DataManager;
import com.cdp.checkboxreminder.managers.DateManager;
import com.cdp.checkboxreminder.managers.MenuManager;
import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.managers.TaskManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TaskManager taskManager;
    MenuManager menuManager;
    DateManager dateManager;
    DataManager dataManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager=new DataManager(this);
        dateManager=new DateManager(this);
        menuManager=new MenuManager(this);
        taskManager=new TaskManager(this);
    }
    public void createTask(View view) {

            taskManager.createTask(
                    String.valueOf(menuManager.getNameEdit().getText()),
                    String.valueOf(menuManager.getDescriptionEdit().getText()),
                    menuManager.getDay(),menuManager.getMonth(),menuManager.getYear()
                    );
            sendDaily();

    }/*
    public void login(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
*/
    public void sendDaily() {
        //Determina la hora para que llegue la notificacion
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 51);
        calendar.set(Calendar.SECOND, 30);

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1); //Añade +1 al dia del mes si la hora determinada ha sido superada.
        }

        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Para acceder al movil cuando la aplicacion no este en uso
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        }
    }

    public void checkTask(View view){
        taskManager.checkTask(view.getId());
    }
    public void removeTask(View view){
        taskManager.removeTask(view.getId());
}
    public void expandMenu(View view){
        menuManager.expand();
    }
    public void expandTask(View view) {
        taskManager.expand(view);
    }
    public MenuManager getMenuManager() {
        return menuManager;
    }
    public DateManager getDateManager() {
        return dateManager;
    }
    public TaskManager getTaskManager() {
        return taskManager;
    }
    public DataManager getDataManager() {
        return dataManager;
    }
}
