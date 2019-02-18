package com.akshanshgusain.notificationcodetutor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    public static final String CHANNELL_ID = "channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.notifi_button);

        //1. Create a Notification channel
        createNotificationChannel();

        mButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
                   switch(v.getId())
                   {
                       case R.id.notifi_button: triggerNotification(); break;
                   }
    }





    private void createNotificationChannel() {
        //NOtification channels only works above android Oreo
          if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O )
          {
              NotificationChannel notificationChannel = new NotificationChannel(CHANNELL_ID,"name: NEWS", NotificationManager.IMPORTANCE_DEFAULT);
              notificationChannel.setDescription("Channel Description.....");
              notificationChannel.setShowBadge(true);
              //Now, we need to register this notification channel..
              //For that we need a notification manager
              NotificationManager notificationManager = getSystemService(NotificationManager.class);
              notificationManager.createNotificationChannel(notificationChannel);

          }
    }

    private void triggerNotification() {
        Intent intent = new Intent(this, NotificationNavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);

        //NOw we'll build the notifications

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNELL_ID)
                                                                      .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                                                                       .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_announcement_black_24dp))
                                       .setContentTitle("This is the notification title")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is the Big text Description....."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setChannelId(CHANNELL_ID)
                .setAutoCancel(true);
        //Adding click trigger
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        //Provide the notification iD   NOT TO BE CONFUSED WITH CHANNEL ID;
        notificationManagerCompat.notify(23, builder.build());
        //<------------------^------- this will create the notification

    }
}
