package com.leper182.fitnesskit.utils;

import android.util.Log;

import com.leper182.fitnesskit.data.Timetable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class JSONUtils {

    private static final String KEY_NAME ="name";
    private static final String KEY_START_TIME ="startTime";
    private static final String KEY_END_TIME ="endTime";
    private static final String KEY_TEACHER ="teacher";
    private static final String KEY_PLACE ="place";
    private static final String KEY_DESCRIPTION ="description";
    private static final String KEY_WEEK_DAY = "weekDay";

    public static ArrayList<Timetable> getTimetablesFromJSON(JSONArray jsonArray) {
        ArrayList<Timetable> result = new ArrayList<>();
        if (jsonArray == null) {
            return result;
        }
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject objectTimetable = jsonArray.getJSONObject(i);
                    String name = objectTimetable.getString(KEY_NAME);
                    String startTime = objectTimetable.getString(KEY_START_TIME);
                    String endTime = objectTimetable.getString(KEY_END_TIME);
                    String teacher = objectTimetable.getString(KEY_TEACHER);
                    String place = objectTimetable.getString(KEY_PLACE);
                    String description = objectTimetable.getString(KEY_DESCRIPTION);
                    int weekDay = objectTimetable.getInt(KEY_WEEK_DAY);
                    Timetable timetable = new Timetable(name,startTime,endTime,teacher,place,description,weekDay);
                    result.add(timetable);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return result;
    }
}