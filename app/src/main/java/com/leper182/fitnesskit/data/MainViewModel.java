package com.leper182.fitnesskit.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static TimetableDatabase database;
    private LiveData<List<Timetable>> timetables;

    public LiveData<List<Timetable>> getTimetables() {
        return timetables;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = TimetableDatabase.getInstance(getApplication());
        timetables = database.timetablesDao().getAllTimetables();
    }

    public Timetable getTimetableById(int id) {
        try {
            return new GetTimetableTask().execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Timetable getTimetableByWeekDay(int weekDay){
        try {
            return new GetTimetableByWeekDayTask().execute(weekDay).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void insertTimetable(Timetable timetable){
        new InsertTimetableTask().execute(timetable);
    }
    public void deleteTimetable(Timetable timetable){
        new DeleteTimetableTask().execute(timetable);
    }
    public void deleteAllTimetables(){
        new DeleteAllTimetablesTask().execute();
    }

    private static class GetTimetableTask extends AsyncTask<Integer, Void, Timetable> {

        @Override
        protected Timetable doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0){
                return database.timetablesDao().getTimetableById(integers[0]);
            }
                return null;
        }
    }

    private static class GetTimetableByWeekDayTask extends AsyncTask<Integer, Void, Timetable> {

        @Override
        protected Timetable doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0){
                return database.timetablesDao().getTimetableByWeekDay(integers[0]);
            }
            return null;
        }
    }

    private static class InsertTimetableTask extends AsyncTask <Timetable, Void ,Void>{

        @Override
        protected Void doInBackground(Timetable... timetables) {
            if (timetables != null && timetables.length > 0){
                database.timetablesDao().insertTimetable(timetables[0]);
            }
            return null;
        }
    }
    private static class DeleteTimetableTask extends AsyncTask <Timetable, Void ,Void>{

        @Override
        protected Void doInBackground(Timetable... timetables) {
            if (timetables != null && timetables.length > 0){
                database.timetablesDao().deleteTimetable(timetables[0]);
            }
            return null;
        }
    }
    private static class DeleteAllTimetablesTask extends AsyncTask <Void, Void ,Void>{

        @Override
        protected Void doInBackground(Void... integers) {
                database.timetablesDao().deleteAllTimetables();

            return null;
        }
    }

}
