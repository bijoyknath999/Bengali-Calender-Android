package com.tempustechnologies.bengali_calendar.repository;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CalRepo {

    public static CalRepo instance;

    public MutableLiveData<List<CalendarData>>  listMutableLiveData;

    public static CalRepo getInstance()
    {
        if (instance == null)
        {
            instance = new CalRepo();
        }
        return instance;
    }

    public LiveData<List<CalendarData>> getCalendarDataList(String month, Context context){

        listMutableLiveData = new MutableLiveData<>();
        List<CalendarData> calendarDataList = new ArrayList<>();


        CalendarData calendarDataAll = CalenderTools.getCurrentDateBangla(context);

        AssetManager assetManager = context.getAssets();

        int t = 0;
        int i = 0;
        try {
            InputStream inputStream = assetManager.open("data.json");
            Gson gson = new Gson();
            Type type = new TypeToken<List<CalendarData>>(){}.getType();
            List<CalendarData> calendarData = gson.fromJson(new InputStreamReader(inputStream), type);

            for (int j=0; j<calendarData.size(); j++)
            {
                if (calendarData.get(j).getBangla_year().equals(calendarDataAll.getBangla_year()) &&
                        calendarData.get(j).getBangla_month_num().equals(month))
                {
                    if (i==0 && calendarData.get(j).getBangla_week_num().equals("1")) {
                        i = 0;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("2")) {
                        calendarDataList.add(0,calendarData.get(j-1));
                        i = 1;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("3")) {
                        calendarDataList.add(0,calendarData.get(j-2));
                        calendarDataList.add(1,calendarData.get(j-1));
                        i = 2;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("4")) {
                        calendarDataList.add(0,calendarData.get(j-3));
                        calendarDataList.add(1,calendarData.get(j-2));
                        calendarDataList.add(2,calendarData.get(j-1));
                        i = 3;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("5")) {
                        calendarDataList.add(0,calendarData.get(j-4));
                        calendarDataList.add(1,calendarData.get(j-3));
                        calendarDataList.add(2,calendarData.get(j-2));
                        calendarDataList.add(3,calendarData.get(j-1));
                        i = 4;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("6")) {
                        calendarDataList.add(0,calendarData.get(j-5));
                        calendarDataList.add(1,calendarData.get(j-4));
                        calendarDataList.add(2,calendarData.get(j-3));
                        calendarDataList.add(3,calendarData.get(j-2));
                        calendarDataList.add(4,calendarData.get(j-1));
                        i = 5;
                    }
                    else if (i==0 && calendarData.get(j).getBangla_week_num().equals("7")) {
                        calendarDataList.add(0,calendarData.get(j-6));
                        calendarDataList.add(1,calendarData.get(j-5));
                        calendarDataList.add(2,calendarData.get(j-4));
                        calendarDataList.add(3,calendarData.get(j-3));
                        calendarDataList.add(4,calendarData.get(j-2));
                        calendarDataList.add(5,calendarData.get(j-1));
                        i = 6;
                    }
                    calendarDataList.add(i,calendarData.get(j));
                    i++;
                    t = j;
                }
            }

            for (int k = i; k < 42; k++)
            {
                t++;
                if (t<calendarData.size())
                    calendarDataList.add(k,calendarData.get(t));
            }

            listMutableLiveData.postValue(calendarDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listMutableLiveData;
    }

}
