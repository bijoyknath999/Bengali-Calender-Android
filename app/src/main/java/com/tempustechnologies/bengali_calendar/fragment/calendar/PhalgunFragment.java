package com.tempustechnologies.bengali_calendar.fragment.calendar;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.adapters.CalendarAdapters;
import com.tempustechnologies.bengali_calendar.databinding.FragmentPhalgunBinding;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;
import com.tempustechnologies.bengali_calendar.viewmodel.CalViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PhalgunFragment extends Fragment {

    private FragmentPhalgunBinding binding;
    private GridLayoutManager gridLayoutManager;
    private List<CalendarData> calendarDataList;
    private CalendarAdapters calendarAdapters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhalgunBinding.inflate(getLayoutInflater());

        calendarDataList = new ArrayList<>();


        gridLayoutManager = new GridLayoutManager(getContext(),7,GridLayoutManager.VERTICAL,false);
        binding.calendarFragmentRecyclerview.setLayoutManager(gridLayoutManager);
        calendarAdapters = new CalendarAdapters(getContext(),calendarDataList);
        binding.calendarFragmentRecyclerview.setAdapter(calendarAdapters);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CalViewModel calViewModel = new ViewModelProvider(this).get(CalViewModel.class);
        calViewModel.getCalendarDataList("11", getContext()).observe(getActivity(), new Observer<List<CalendarData>>() {
            @Override
            public void onChanged(List<CalendarData> calendarData) {
                if (calendarData!=null)
                {
                    calendarDataList.addAll(calendarData);
                    calendarAdapters.notifyDataSetChanged();
                }
            }
        });

    }
}