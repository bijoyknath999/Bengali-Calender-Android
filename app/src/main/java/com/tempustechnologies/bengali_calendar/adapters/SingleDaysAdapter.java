package com.tempustechnologies.bengali_calendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempustechnologies.bengali_calendar.R;

import java.util.List;

public class SingleDaysAdapter extends RecyclerView.Adapter<SingleDaysAdapter.ViewHolder> {

    private List<String> stringList;

    public SingleDaysAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public SingleDaysAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_days, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleDaysAdapter.ViewHolder holder, int position) {
        String text = stringList.get(position);
        if (text != null) {
            holder.textView.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_special_days_text);
        }
    }
}
