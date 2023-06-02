package com.tempustechnologies.bengali_calendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.adapters.CalendarAdapters;
import com.tempustechnologies.bengali_calendar.adapters.TabAdapter;
import com.tempustechnologies.bengali_calendar.fragment.calendar.AasharFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.AashinFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.AgrahanFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.BhadraFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.BoishakhFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.ChaitraFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.JaisthoFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.KartikFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.MaaghFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.PhalgunFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.PoushFragment;
import com.tempustechnologies.bengali_calendar.fragment.calendar.ShrabanFragment;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private DachshundTabLayout tabLayout;
    private TabAdapter tabAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        tabLayout= findViewById(R.id.calendar_tablayout);
        viewPager= findViewById(R.id.calendar_viewpager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(12);
        viewPager.setAdapter(tabAdapter);

        tabAdapter.addFragment(new BoishakhFragment(), "Boishakh");
        tabAdapter.addFragment(new JaisthoFragment(), "Jaistho");
        tabAdapter.addFragment(new AasharFragment(), "Aashar");
        tabAdapter.addFragment(new ShrabanFragment(), "Shraban");
        tabAdapter.addFragment(new BhadraFragment(), "Bhadra");
        tabAdapter.addFragment(new AashinFragment(), "Aashin");
        tabAdapter.addFragment(new KartikFragment(), "Kartik");
        tabAdapter.addFragment(new AgrahanFragment(), "Agrahan");
        tabAdapter.addFragment(new PoushFragment(), "Poush");
        tabAdapter.addFragment(new MaaghFragment(), "Maagh");
        tabAdapter.addFragment(new PhalgunFragment(), "Phalgun");
        tabAdapter.addFragment(new ChaitraFragment(), "Chaitra");
    }
}