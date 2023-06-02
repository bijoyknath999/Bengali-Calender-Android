package com.tempustechnologies.bengali_calendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.adapters.HomeCalAdapters;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CalendarData> calendarDataList;
    private GridLayoutManager gridLayoutManager;
    private HomeCalAdapters homeCalAdapters;
    private TextView CurrentMonth, CurrentBanglaDate, CurrentEnglishBanglaDate;
    private ImageButton CalendarBtn;

    private ImageButton TodaysClick, CalendarClick, DinTarikhClick, SettingsClick, OnnoyoClick, ShareClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarDataList = new ArrayList<>();
        recyclerView = findViewById(R.id.home_recyclerview);
        CurrentMonth = findViewById(R.id.home_current_month);
        CurrentBanglaDate = findViewById(R.id.home_current_bangla_date);
        CurrentEnglishBanglaDate = findViewById(R.id.home_current_english_bangla_date);
        TodaysClick = findViewById(R.id.main_todays_click);
        CalendarClick = findViewById(R.id.main_calender_click);
        DinTarikhClick = findViewById(R.id.main_din_tarikh_click);
        SettingsClick = findViewById(R.id.main_settings_click);
        OnnoyoClick = findViewById(R.id.main_others_click);
        ShareClick = findViewById(R.id.main_share_click);


        CalendarData calendarDataAll = CalenderTools.getCurrentDateBangla(MainActivity.this);

        CurrentMonth.setText(CalenderTools.getCurrentMonthYear());
        CurrentBanglaDate.setText(calendarDataAll.getBangla_date());
        CurrentEnglishBanglaDate.setText(CalenderTools.getCurrentDateEngBn());

        gridLayoutManager = new GridLayoutManager(this,7,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        homeCalAdapters = new HomeCalAdapters(this,calendarDataList);
        recyclerView.setAdapter(homeCalAdapters);

        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("data.json");
            Gson gson = new Gson();
            Type type = new TypeToken<List<CalendarData>>(){}.getType();
            List<CalendarData> calendarData = gson.fromJson(new InputStreamReader(inputStream), type);

            int i = 0;
            for (CalendarData calendarData1 : calendarData)
            {
                if (calendarData1.getBangla_year().equals(calendarDataAll.getBangla_year()) &&
                        calendarData1.getBangla_month_num().equals(calendarDataAll.getBangla_month_num()))
                {
                    if (i==0 && calendarData1.getBangla_week_num().equals("1")) {
                        i = 0;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("2")) {
                        calendarDataList.add(0,null);
                        i = 1;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("3")) {
                        calendarDataList.add(0,null);
                        calendarDataList.add(1,null);
                        i = 2;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("4")) {
                        calendarDataList.add(0,null);
                        calendarDataList.add(1,null);
                        calendarDataList.add(2,null);
                        i = 3;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("5")) {
                        calendarDataList.add(0,null);
                        calendarDataList.add(1,null);
                        calendarDataList.add(2,null);
                        calendarDataList.add(3,null);
                        i = 4;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("6")) {
                        calendarDataList.add(0,null);
                        calendarDataList.add(1,null);
                        calendarDataList.add(2,null);
                        calendarDataList.add(3,null);
                        calendarDataList.add(4,null);
                        i = 5;
                    }
                    else if (i==0 && calendarData1.getBangla_week_num().equals("7")) {
                        calendarDataList.add(0,null);
                        calendarDataList.add(1,null);
                        calendarDataList.add(2,null);
                        calendarDataList.add(3,null);
                        calendarDataList.add(4,null);
                        calendarDataList.add(5,null);
                        i = 6;
                    }

                    calendarDataList.add(i,calendarData1);
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeCalAdapters.notifyDataSetChanged();


        CalendarClick.setOnClickListener(v -> startActivity(new Intent(this,CalendarActivity.class)));
        SettingsClick.setOnClickListener(v -> startActivity(new Intent(this,Settings.class)));
        DinTarikhClick.setOnClickListener(v -> startActivity(new Intent(this,DinTarikhActivity.class)));

        startActivity(new Intent(this, CalendarActivity.class));

    }
}
