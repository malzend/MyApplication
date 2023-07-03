package com.example.myapplication.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;

public class notification extends BroadcastReceiver {

    String channel_ID = "test";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context,intent.getStringExtra("key"),Toast.LENGTH_LONG).show();
        creteNotificationChannel(context,channel_ID);
        Notification n = new NotificationCompat.Builder (context, channel_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("NotificationTest").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService (Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, n);
    }

    private void creteNotificationChannel(Context context, String CHANNEL_ID){
            CharSequence name = context.getResources().getString (R.string.channel_name);
            String description = context.getString (R.string.channel_description);
            int importance = NotificationManager. IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel (CHANNEL_ID, name, importance);
            channel.setDescription(description);
// Register the channel with the system; you can't change the importance
// or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService (NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
    }
}