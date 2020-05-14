package com.leper182.fitnesskit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leper182.fitnesskit.R;
import com.leper182.fitnesskit.data.DayOfWeek;

import java.util.ArrayList;

public class DayOfWeekAdapter extends RecyclerView.Adapter<DayOfWeekAdapter.DayViewHolder> {

    private OnDayClickLictener onDayClickLictener;
    private ArrayList<DayOfWeek> daysOfWeek;

    public DayOfWeekAdapter(){
        daysOfWeek = new ArrayList<>();
    }
    public void setDaysOfWeek(ArrayList<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public void setOnDayClickLictener(OnDayClickLictener onDayClickLictener) {
        this.onDayClickLictener = onDayClickLictener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_of_week_icon,parent,false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DayOfWeek dayOfWeek = daysOfWeek.get(position);
        holder.textViewDayOfWeek.setText(dayOfWeek.getName());
    }

    @Override
    public int getItemCount() {
        return daysOfWeek.size();
    }

    public interface OnDayClickLictener{
        void onDayClick(int position);
    }
    class DayViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDayOfWeek;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onDayClickLictener != null){
                        onDayClickLictener.onDayClick(getAdapterPosition());
                    }

                }
            });
        }
    }
}
