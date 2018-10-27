package com.google.firebase.quickstart.fcs;



import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "BANANEALARM";
    Intent intent;
    PendingIntent pendingIntent;
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "BroadcastReceiver has received alarm intent.");
        Intent service1 = new Intent(context, AlarmService.class);
        context.startService(service1);

    }

}


//    TextView alarmReceiver;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alarm_receiver);
//
//        alarmReceiver = findViewById(R.id.alarmText);
//
//        Intent intent =getIntent();
//        String name = intent.getStringExtra("alarmText");
//        if(name!=null)
//        {
//            alarmReceiver.setText(name);
//        }
//
//        alarmReceiver.setText("");
//    }
//}