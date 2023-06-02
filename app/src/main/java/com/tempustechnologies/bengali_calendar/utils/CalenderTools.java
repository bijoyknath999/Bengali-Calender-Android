package com.tempustechnologies.bengali_calendar.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tempustechnologies.bengali_calendar.models.CalendarData;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalenderTools {
    public static String getCurrentMonthYear()
    {
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("bn");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", locale);
        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrentDateEngBn()
    {
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("bn");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale);
        return dateFormat.format(calendar.getTime());
    }

    public static String getSpecificDateEngBn(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Locale locale = new Locale("bn");
        SimpleDateFormat resultFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale);
        return resultFormat.format(date);
    }


    public static String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("en");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", locale);
        return dateFormat.format(calendar.getTime());
    }

    public static CalendarData getCurrentDateBangla(Context context){
        CalendarData calendarst = new CalendarData();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CalendarData>>(){}.getType();
        List<CalendarData> calendarData = gson.fromJson(new InputStreamReader(inputStream), type);
        for (CalendarData calendarData1 : calendarData)
        {
            if (calendarData1.getEnglish_date().equals(getCurrentDate()))
                calendarst = calendarData1;
        }
        return calendarst;
    }

    public static String getCurrentMonthFromJson(Context context)
    {
        String month = null;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CalendarData>>(){}.getType();
        List<CalendarData> calendarData = gson.fromJson(new InputStreamReader(inputStream), type);
        for (CalendarData calendarData1 : calendarData)
        {
            if (calendarData1.getEnglish_date().equals(getCurrentDate()))
                month = calendarData1.getBangla_month_num();
        }
        return month;
    }

    public static String getCurrentYearFromJson(Context context)
    {
        String year = "";
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CalendarData>>(){}.getType();
        List<CalendarData> calendarData = gson.fromJson(new InputStreamReader(inputStream), type);
        for (CalendarData calendarData1 : calendarData)
        {
            if (calendarData1.getEnglish_date().equals(getCurrentDate()))
                year = calendarData1.getBangla_year();
        }
        return year;
    }

    public static String convertToBanglaDigits(String input) {
        String[] banglaDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                output += banglaDigits[digit];
            } else {
                output += c;
            }
        }
        return output;
    }

    public static CalendarData getCalendarByID(Context context,int id){
        CalendarData calendarData = new CalendarData();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CalendarData>>(){}.getType();
        List<CalendarData> calendarAll = gson.fromJson(new InputStreamReader(inputStream), type);
        for (CalendarData calendar : calendarAll)
        {
            if (calendar.getId().equals(String.valueOf(id)))
                calendarData = calendar;
        }
        return calendarData;
    }

    public static String getFirstPartString(String value, String speciStr)
    {
        return value.substring(0, value.indexOf(speciStr));
    }

    public static String getLastPartString(String value, String speciStr)
    {
        return value.substring(value.indexOf(speciStr) + 1);
    }

    public static List<String> getStringToList(String value, String speciStr)
    {
        String[] substrings = value.split(speciStr);

        List<String> substringList = Arrays.asList(substrings);

        return substringList;
    }
}
