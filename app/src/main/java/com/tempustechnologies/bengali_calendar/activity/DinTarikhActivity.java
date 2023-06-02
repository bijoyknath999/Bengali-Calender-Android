package com.tempustechnologies.bengali_calendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.tempustechnologies.bengali_calendar.R;

public class DinTarikhActivity extends AppCompatActivity {

    private CardView GovtHolidaysClick, AgeCalculator, DateDiffClick, TodaysFromCalClick, DeshBideshClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_din_tarikh);

        GovtHolidaysClick = findViewById(R.id.din_tarikh_govt_holidays_click);
        AgeCalculator = findViewById(R.id.din_tarikh_age_calculator_click);
        DateDiffClick = findViewById(R.id.din_tarikh_date_diff_click);
        TodaysFromCalClick = findViewById(R.id.din_tarikh_aaj_theke_count_click);
        DeshBideshClick = findViewById(R.id.din_tarikh_desh_bidesher_somoy_click);


        GovtHolidaysClick.setOnClickListener(v -> {
            startActivity(new Intent(this, GovtHolidays.class));
        });

        AgeCalculator.setOnClickListener(v -> {
            startActivity(new Intent(this, AgeCalculator.class));
        });

        DateDiffClick.setOnClickListener(v -> {
            startActivity(new Intent(this, DateDifference.class));
        });

        TodaysFromCalClick.setOnClickListener(v -> {
            startActivity(new Intent(this, CalFromToday.class));
        });

        DeshBideshClick.setOnClickListener(v -> {
            startActivity(new Intent(this, CountryTimes.class));
        });

    }
}