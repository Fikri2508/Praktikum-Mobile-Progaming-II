package com.example.listlearning;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application{

    private static MyApp INSTANCE;
    public static Appdatabase db;

    static MyApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                Appdatabase.class, "mahasiswa")
                .addMigrations(DataBaseMigrations.MIGRATIONS)
                .allowMainThreadQueries()
                .build();

        INSTANCE = this;
    }

    public Appdatabase getDataBase(){
        return db;
    }
}
