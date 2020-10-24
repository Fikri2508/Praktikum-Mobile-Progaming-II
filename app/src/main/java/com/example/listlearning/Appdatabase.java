package com.example.listlearning;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mahasiswa.class}, version = 1)
public abstract class Appdatabase extends RoomDatabase {
    public abstract MahasiswaDao userDao();
}
