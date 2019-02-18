package com.akshanshgusain.notificationcodetutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationNavigationActivity extends AppCompatActivity {
     private TextView mDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_navigation);
        mDesc = findViewById(R.id.Content);

    }
}
