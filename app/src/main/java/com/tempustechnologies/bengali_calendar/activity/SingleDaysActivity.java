package com.tempustechnologies.bengali_calendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.adapters.SingleDaysAdapter;
import com.tempustechnologies.bengali_calendar.databinding.ActivitySingleDaysBinding;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SingleDaysActivity extends AppCompatActivity {

    private ActivitySingleDaysBinding binding;
    private List<String> stringList;
    private SingleDaysAdapter singleDaysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingleDaysBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stringList = new ArrayList<>();

        String calId = getIntent().getStringExtra("calId");

        CalendarData data = CalenderTools.getCalendarByID(SingleDaysActivity.this, Integer.parseInt(calId));

        binding.singleDaysBangleDate.setText(data.getBangla_date());
        try {
            binding.singleDaysEngBanDate.setText(CalenderTools.getSpecificDateEngBn(data.getEnglish_date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        binding.singleDaysSpecial.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        singleDaysAdapter = new SingleDaysAdapter(stringList);
        binding.singleDaysSpecial.setAdapter(singleDaysAdapter);

        stringList.addAll(CalenderTools.getStringToList(data.getBangla_special(),"\\$"));
        singleDaysAdapter.notifyDataSetChanged();
    }
}