package com.example.listlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_data);

        Button btnTambah = findViewById(R.id.btnTambah);
        Button btnLihat = findViewById(R.id.btnlihat);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomDataActivity.this, AddRoomDataActivity.class);
                startActivity(intent);
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomDataActivity.this, ViewRoomDataActivity.class);
                startActivity(intent);
            }
        });
    }
}