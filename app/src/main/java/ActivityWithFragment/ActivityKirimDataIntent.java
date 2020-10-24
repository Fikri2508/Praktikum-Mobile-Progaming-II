package ActivityWithFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listlearning.R;

public class ActivityKirimDataIntent extends AppCompatActivity {

    String welcomeMessage;
    TextView TextViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_data_intent);
        TextViewWelcome = findViewById(R.id.text1);

        Intent intent = getIntent();
        intent.getStringExtra("Welcome");
        TextViewWelcome.setText(welcomeMessage);
    }

    protected void onResume(){
        super.onResume();
    }
}