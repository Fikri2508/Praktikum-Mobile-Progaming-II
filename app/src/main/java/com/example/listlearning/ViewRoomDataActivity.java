package com.example.listlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.listlearning.MyApp.db;

public class ViewRoomDataActivity extends AppCompatActivity {

    RecyclerView myRecycleview;
    RecycleViewAdapter recycleAdapter;
    List<Mahasiswa> listmahasiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_data);
        myRecycleview = findViewById(R.id.myRecyclerview);

        fetchDataFromRoom();
        initRecycleView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                Appdatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listmahasiswa = db.userDao().getAll();

        for (int i = 0 ;i <listmahasiswa.size(); i++){
            Log.e("Aplikasi", listmahasiswa.get(i).getAlamat()+i);
            Log.e("Aplikasi", listmahasiswa.get(i).getKejuruan()+i);
            Log.e("Aplikasi", listmahasiswa.get(i).getNama()+i);
            Log.e("Aplikasi", listmahasiswa.get(i).getNim()+i);
        }
    }

    private void initRecycleView() {
        myRecycleview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycleview.setLayoutManager(llm);
        recycleAdapter = new RecycleViewAdapter(this,listmahasiswa);
    }

    private void setAdapter(){
        myRecycleview.setAdapter(recycleAdapter);

    }
}