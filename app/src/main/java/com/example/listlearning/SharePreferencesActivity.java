package com.example.listlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharePreferencesActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private SharePrefManager sharePrefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        editText = findViewById(R.id.eTinput);
        textView = findViewById(R.id.tvOutput);
        button = findViewById(R.id.btSave);
        sharePrefManager = new SharePrefManager(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                Toast.makeText(getApplicationContext(),"Data Tersimpan", Toast.LENGTH_SHORT);
            }
        });

    }

    private void getData() {
        String getKonten = editText.getText().toString();
        sharePrefManager.saveString(getKonten);

        textView.setText(sharePrefManager.getString());
    }
}