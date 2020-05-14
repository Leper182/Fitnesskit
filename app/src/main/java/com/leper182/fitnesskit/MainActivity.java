package com.leper182.fitnesskit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.leper182.fitnesskit.adapters.DayOfWeekAdapter;
import com.leper182.fitnesskit.adapters.TimetableAdapter;
import com.leper182.fitnesskit.data.DayOfWeek;
import com.leper182.fitnesskit.data.MainViewModel;
import com.leper182.fitnesskit.data.Timetable;
import com.leper182.fitnesskit.utils.JSONUtils;
import com.leper182.fitnesskit.utils.NetworkUtils;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDaysOfWeek;
    private DayOfWeekAdapter dayOfWeekAdapter;
    private ArrayList<DayOfWeek> dayOfWeeks = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewDaysOfWeek = findViewById(R.id.recyclerViewDaysOfWeek);
        dayOfWeekAdapter = new DayOfWeekAdapter();
        recyclerViewDaysOfWeek.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        dayOfWeeks.add(new DayOfWeek(1,"Понедельник"));
        dayOfWeeks.add(new DayOfWeek(2,"Вторник"));
        dayOfWeeks.add(new DayOfWeek(3,"Среда"));
        dayOfWeeks.add(new DayOfWeek(4,"Четверг"));
        dayOfWeeks.add(new DayOfWeek(5,"Пятница"));
        dayOfWeeks.add(new DayOfWeek(6,"Суббота"));
        dayOfWeeks.add(new DayOfWeek(6,"Воскресенье"));
        dayOfWeekAdapter.setDaysOfWeek(dayOfWeeks);
        dayOfWeekAdapter.setOnDayClickLictener(new DayOfWeekAdapter.OnDayClickLictener() {
            @Override
            public void onDayClick(int position) {
                Intent intent = new Intent(getApplicationContext(),DayActivity.class);
                intent.putExtra("position",position+1);
                startActivity(intent);
            }
        });
        recyclerViewDaysOfWeek.setAdapter(dayOfWeekAdapter);



    }

}
