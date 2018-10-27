package com.google.firebase.quickstart.fcs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.quickstart.fcs.R;
import com.google.firebase.quickstart.fcs.fragment.MyPostsFragment;
import com.google.firebase.quickstart.fcs.fragment.MyTopPostsFragment;
import com.google.firebase.quickstart.fcs.fragment.RecentPostsFragment;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class Launsher extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    private Button posts, financial_support, maps, chat;
    private static final String TAG = "BANANEALARM";
    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    Button bananaButton;
    TextView notificationCount;
    TextView notificationCountLabel;
    int mNotificationCount;
    static final String NOTIFICATION_COUNT = "notificationCount";

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launsher);

        //getWindow().setStatusBarColor(Color.RED);
        getWindow().setStatusBarColor(getResources().getColor(R.color.pink)); // RIGHT

        posts = findViewById(R.id.posts);
        financial_support = findViewById(R.id.financial_support);
        maps = findViewById(R.id.maps);
        chat = findViewById(R.id.chat);

        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        financial_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FinancialSupport.class);
                startActivity(i);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(i);
            }
        });


//        if (savedInstanceState != null) {
//            // Restore value of members from saved state
//            mNotificationCount = savedInstanceState.getInt(NOTIFICATION_COUNT);
//        }
//
//        setContentView(R.layout.activity_launsher);
//        bananaButton = (Button)findViewById(R.id.bananaButton);
//        notificationCount = (TextView)findViewById(R.id.notificationCount);
//        notificationCountLabel = (TextView)findViewById(R.id.notificationCountLabel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId())
        {
            case R.id.social_network: //Log.d("map","Hybrid");
                //Intent i = new Intent(getApplicationContext(),PostDetailActivity.class);
                //startActivity(i);
                ; break;

            case R.id.chat:
                Intent chat = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(chat);
                ; break;

            case R.id.financial_support:
                Intent financial_support = new Intent(getApplicationContext(),FinancialSupport.class);
                startActivity(financial_support);
                ; break;

            case R.id.maps:
                Intent maps = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(maps);
                ; break;

            case R.id.mylocationmarker: Log.d("map","mylocationmarker");
                break;

        }
        return super.onOptionsItemSelected(item);
    }
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save the user's current game state
//        savedInstanceState.putInt(NOTIFICATION_COUNT, mNotificationCount);
//        super.onSaveInstanceState(savedInstanceState);
//    }
//
//    @Override
//    protected void onNewIntent( Intent intent ) {
//        Log.i( TAG, "onNewIntent(), intent = " + intent );
//        if (intent.getExtras() != null)
//        {
//            Log.i(TAG, "in onNewIntent = " + intent.getExtras().getString("test"));
//        }
//        super.onNewIntent( intent );
//        setIntent( intent );
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void triggerAlarm(View v){
//        setAlarm();
//        bananaButton.setVisibility(View.GONE);
//        notificationCountLabel.setVisibility(View.VISIBLE);
//        notificationCount.setVisibility(View.VISIBLE);
//        notificationCount.setText("0");
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void setAlarm(){
//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        alarmIntent = new Intent(Launsher.this, AlarmReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(  Launsher.this, 0, alarmIntent, 0);
//
//        Calendar alarmStartTime = Calendar.getInstance();
//        alarmStartTime.add(Calendar.MINUTE, 2);
//        alarmManager.setRepeating(AlarmManager.RTC, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);
//        Log.i(TAG,"Alarms set every two minutes.");
//
//    }
//
//    private int getInterval(){
//        int seconds = 60;
//        int milliseconds = 1000;
//        int repeatMS = seconds * 2 * milliseconds;
//        return repeatMS;
//    }
//
//    @Override
//    protected void onStart(){
//        super.onStart();
//        updateUI();
//    }
//
//    public void cancelNotifications(){
//        Log.i(TAG,"All notifications cancelled.");
//    }
//
//    public void updateUI(){
//        MyAlarm app = (MyAlarm)getApplicationContext();
//        mNotificationCount = app.getNotificationCount();
//        notificationCount.setText(Integer.toString(mNotificationCount));
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        if(this.getIntent().getExtras() != null){
//            Log.i(TAG,"extras: " + this.getIntent().getExtras());
//            updateUI();
//
//        }
//    }



}

