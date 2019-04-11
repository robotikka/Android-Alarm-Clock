package com.ctse.alarmsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String ringtone = intent.getStringExtra("ringtone");

        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show();
        System.out.println("Alarm Fired");
        Log.i("broadcast receiver", "Alarm Fired");

        Intent alarmFiredIntent = new Intent(context, AlarmFiredActivity.class);

        alarmFiredIntent.putExtra("lock", true);
        alarmFiredIntent.putExtra("ringtone", ringtone);

        alarmFiredIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(alarmFiredIntent);
    }
}
