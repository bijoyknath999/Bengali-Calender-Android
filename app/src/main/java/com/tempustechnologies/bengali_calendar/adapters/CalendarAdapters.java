package com.tempustechnologies.bengali_calendar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.activity.SingleDaysActivity;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarAdapters extends RecyclerView.Adapter<CalendarAdapters.ViewHolder> {

    private Context context;
    private List<CalendarData> calendarDataList;

    public CalendarAdapters(Context context, List<CalendarData> calendarDataList) {
        this.context = context;
        this.calendarDataList = calendarDataList;
    }

    @NonNull
    @Override
    public CalendarAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calender,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapters.ViewHolder holder, int position) {
        CalendarData calendarData = calendarDataList.get(position);
        if (calendarData!=null)
        {
            holder.BanglaText.setText(calendarData.getBangla_date().substring(0,2));

            String dateString = calendarData.getEnglish_date();
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM");
            Date date;
            String formattedDate = "";
            try {
                date = inputFormat.parse(dateString);
                formattedDate = outputFormat.format(date);
                holder.EnglishText.setText(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, SingleDaysActivity.class);
                intent.putExtra("calId",calendarData.getId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return calendarDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView EnglishText, BanglaText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BanglaText = itemView.findViewById(R.id.item_calender_bangla);
            EnglishText = itemView.findViewById(R.id.item_calender_english);
        }
    }
}
