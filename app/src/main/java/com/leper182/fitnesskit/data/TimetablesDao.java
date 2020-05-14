package com.leper182.fitnesskit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TimetablesDao {
    @Query("SELECT * FROM timetables")
    LiveData<List<Timetable>> getAllTimetables();

    @Query("SELECT * FROM timetables WHERE id == :timetableId")
    Timetable getTimetableById(int timetableId);

    @Query("SELECT * FROM timetables WHERE weekDay == :weekDay")
    Timetable getTimetableByWeekDay(int weekDay);

    @Query("DELETE FROM timetables")
    void deleteAllTimetables();

    @Insert
    void insertTimetable(Timetable timetable);

    @Delete
    void deleteTimetable(Timetable timetable);

}
