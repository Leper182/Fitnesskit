package com.leper182.fitnesskit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leper182.fitnesskit.R;
import com.leper182.fitnesskit.data.Timetable;

import java.util.ArrayList;
import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {

    private OnTitleClickListener onTitleClickListener;
    private List<Timetable> timetables = new ArrayList<>();

    public TimetableAdapter(){
        timetables = new ArrayList<>();
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fitness_title_icon,parent,false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        Timetable timetable = timetables.get(position);
        holder.textViewTitleName.setText(timetable.getName());
        holder.textViewTitleStartTime.setText(timetable.getStartTime());
        holder.textViewTitleEndTime.setText(timetable.getEndTime());
        holder.textViewTitleDayOfWeek.setText(""+timetable.getWeekDay());
    }

    @Override
    public int getItemCount() {
        return timetables.size();
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
        notifyDataSetChanged();
    }
    public void addTimetables(List<Timetable> timetables) {
        this.timetables.addAll(timetables);
        notifyDataSetChanged();
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public interface OnTitleClickListener {
        void onTitleClick(int position);
    }

    class TimetableViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitleName;
        TextView textViewTitleStartTime;
        TextView textViewTitleEndTime;
        TextView textViewTitleDayOfWeek;

        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitleName = itemView.findViewById(R.id.textViewName);
            textViewTitleStartTime = itemView.findViewById(R.id.textViewTimeStart);
            textViewTitleEndTime = itemView.findViewById(R.id.textViewTimeEnd);
            textViewTitleDayOfWeek = itemView.findViewById(R.id.textViewTitleDayOfWeek);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTitleClickListener != null){
                        onTitleClickListener.onTitleClick(getAdapterPosition());
                    }
                }
            });
        }
    }

}
