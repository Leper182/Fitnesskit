package com.leper182.fitnesskit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.leper182.fitnesskit.adapters.TimetableAdapter;
import com.leper182.fitnesskit.data.MainViewModel;
import com.leper182.fitnesskit.data.Timetable;
import com.leper182.fitnesskit.utils.JSONUtils;
import com.leper182.fitnesskit.utils.NetworkUtils;

import org.json.JSONArray;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class DayActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTitle;
    private TimetableAdapter timetableAdapter;
    private ArrayList<Timetable> timetables = new ArrayList<>();
    private ArrayList<Timetable> timetablesForDay = new ArrayList<>();

    private MainViewModel viewModel;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        timetableAdapter = new TimetableAdapter();
        recyclerViewTitle = findViewById(R.id.recyclerViewTitle);
        recyclerViewTitle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        timetableAdapter.setTimetables(timetablesForDay);
        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra("position")) {
            id = intent.getIntExtra("position", -1);
        }
        LiveData<List<Timetable>> timetablesFromLiveData = viewModel.getTimetables();
        timetablesFromLiveData.observe(this, new Observer<List<Timetable>>() {
            @Override
            public void onChanged(List<Timetable> timetablesFromLiveData) {
                List<Timetable> timetableForDayLD = new ArrayList<>();
                for (int i = 0; i < timetablesFromLiveData.size(); i++) {
                    Timetable timetable = timetablesFromLiveData.get(i);
                    int weekDay = timetable.getWeekDay();
                    if (id == weekDay) {
                        timetableForDayLD.add(timetable);
                    }
                }
                timetableAdapter.setTimetables(timetableForDayLD);
            }
        });
        timetableAdapter.setOnTitleClickListener(new TimetableAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(int position) {
                Timetable timetable = timetableAdapter.getTimetables().get(position);
                int id = timetable.getId();
                Intent intentShowDescription = new Intent(getApplicationContext(),DescriptionActivity.class);
                intentShowDescription.putExtra("id", id);
                startActivity(intentShowDescription);
                Toast.makeText(DayActivity.this, ""+id, Toast.LENGTH_SHORT).show();

            }
        });
        downloadData();

        recyclerViewTitle.setAdapter(timetableAdapter);


    }

    private void downloadData() {
        JSONArray jsonArray = null;
        try {
            jsonArray = NetworkUtils.gerJSONFromNetwork();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        timetables.addAll(JSONUtils.getTimetablesFromJSON(jsonArray));
        if (timetables != null && !timetables.isEmpty()) {
            viewModel.deleteAllTimetables();
            for (Timetable timetable : timetables) {
                viewModel.insertTimetable(timetable);
            }
        }
    }

}
