package com.tempustechnologies.bengali_calendar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempustechnologies.bengali_calendar.R;
import com.tempustechnologies.bengali_calendar.models.CalendarData;
import com.tempustechnologies.bengali_calendar.utils.CalenderTools;

import org.w3c.dom.Text;

import java.util.List;

public class HomeCalAdapters extends RecyclerView.Adapter<HomeCalAdapters.ViewHolder> {

    private Context context;
    private List<CalendarData> calendarDataList;

    public HomeCalAdapters(Context context, List<CalendarData> calendarDataList) {
        this.context = context;
        this.calendarDataList = calendarDataList;
    }

    @NonNull
    @Override
    public HomeCalAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cal_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCalAdapters.ViewHolder holder, int position) {
        CalendarData calendarData = calendarDataList.get(position);
        if (calendarDataList.size()>0)
        {
            if (calendarData!=null) {
                holder.BanglaDateNo.setText(calendarData.getBangla_date().substring(0, 2));

                if (calendarData.getEnglish_date().equals(CalenderTools.getCurrentDate())) {
                    holder.linearLayout.setBackground(context.getDrawable(R.drawable.bg_wt3));
                    holder.BanglaDateNo.setTextColor(context.getColor(R.color.color_blue));
                }
                else if (position == 0
                        || position == 7
                        || position == 14
                        || position == 21
                        || position == 28)
                    holder.linearLayout.setBackground(context.getDrawable(R.drawable.bg_wt2));

            }
        }
    }

    @Override
    public int getItemCount() {
        return calendarDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView BanglaDateNo;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BanglaDateNo = itemView.findViewById(R.id.item_cal_home_bangla_no);
            linearLayout = itemView.findViewById(R.id.item_cal_home_layout);
        }
    }
}
