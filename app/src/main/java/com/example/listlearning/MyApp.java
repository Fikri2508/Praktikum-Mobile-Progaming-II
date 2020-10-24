package com.example.listlearning;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application{

    public static Appdatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                Appdatabase.class, "mahasiswa").allowMainThreadQueries().build();
    }
    
}
