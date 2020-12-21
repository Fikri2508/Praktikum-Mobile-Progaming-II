package com.example.listlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.listlearning.activitywithfragment.ActivityDanFragment;
import com.example.listlearning.activitywithfragment.ActivityKirimDataIntent;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    private TextView TextTimer;
    private Button mButtonStart;
    private Button mButtonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnToShared =  findViewById(R.id.btnToSharedPref);
        Button btnToRoom =  findViewById(R.id.btnToRoomData);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btnLogin =  findViewById(R.id.btnLogin);
        TextTimer = findViewById(R.id.tv_timer);
        mButtonStart = findViewById(R.id.bt_start);
        mButtonStop = findViewById(R.id.bt_stop);


        setupView();


        btnToShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SharePreferencesActivity.class);
                startActivity(i);
            }
        });

        btnToRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RoomDataActivity.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityKirimDataIntent.class);
                intent.putExtra("Welcome" , "Hello Dari snap activity");

                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityDanFragment.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, login_Activity.class);
                startActivity(i);
            }
        });

    }

    private void setupView() {
        registerReceiver(broadcastReceiver, new IntentFilter(TimerService.BROADCAST_ACTION));

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, TimerService.class);
                intent.setAction(TimerService.ACTION_START_FOREGROUND_SERVICES);
                startService(intent);
            }
        });
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextTimer.setText("0:00");
                Intent intent = new Intent( MainActivity.this, TimerService.class);
                intent.setAction(TimerService.ACTION_STOP_FOREGROUND_SERVICES);
                stopService(intent);
            }
        });


    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    private void updateUI(Intent intent){
        int mins = intent.getIntExtra("mins", 0);
        int secs = intent.getIntExtra("secs", 0);
        TextTimer.setText("" + mins + ":" + String.format("%02d", secs));


    }
}