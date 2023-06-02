package com.tempustechnologies.bengali_calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        startActivity(new Intent(this, com.tempustechnologies.bengali_calendar.activity.MainActivity.class));
    }
}