package com.leper182.fitnesskit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.leper182.fitnesskit.data.MainViewModel;
import com.leper182.fitnesskit.data.Timetable;

public class DescriptionActivity extends AppCompatActivity {

    private TextView textViewName, textViewTimeStart, textViewTimeEnd, textViewTeacher, textViewPlace, textViewDescription;
    private int id;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        textViewName = findViewById(R.id.textViewName);
        textViewTimeStart = findViewById(R.id.textViewTimeStart);
        textViewTimeEnd = findViewById(R.id.textViewTimeEnd);
        textViewTeacher = findViewById(R.id.textViewTeacher);
        textViewPlace = findViewById(R.id.textViewPlace);
        textViewDescription = findViewById(R.id.textViewDescription);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")){
            id = intent.getIntExtra("id",-1);
        }
        Timetable timetable = viewModel.getTimetableById(id);
        textViewName.setText(timetable.getName());
        textViewTimeStart.setText(timetable.getStartTime());
        textViewTimeEnd.setText(timetable.getEndTime());
        textViewTeacher.setText(timetable.getTeacher());
        textViewPlace.setText(timetable.getPlace());
        textViewDescription.setText(timetable.getDescription());

    }
}
