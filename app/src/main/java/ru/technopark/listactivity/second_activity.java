package ru.technopark.listactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class second_activity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        TextView textView = findViewById(R.id.second);

        Intent intent = getIntent();
        int intExtra = intent.getIntExtra(MainActivity.MY_EXTRA, -1);
        textView.setText(intExtra + "");
    }
}
