package com.tempustechnologies.bengali_calendar.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.repository.CalRepo;

import java.util.List;

public class CalViewModel extends ViewModel {

    public CalRepo calRepo = CalRepo.getInstance();

    public LiveData<List<CalendarData>> getCalendarDataList(String month, Context context)
    {
        return calRepo.getCalendarDataList(month,context);
    }


}
