package com.leper182.fitnesskit.utils;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {
    private static final String BASE_URL = "https://sample.fitnesskit-admin.ru/schedule/get_group_lessons_v2/1/";

    //создаем json из ссылки
    public static JSONArray gerJSONFromNetwork() throws MalformedURLException {
        URL url = new URL(BASE_URL);
        JSONArray result = null;
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static class JSONLoadTask extends AsyncTask<URL,Void, JSONArray>{

        @Override
        protected JSONArray doInBackground(URL... urls) {
            JSONArray result = null;
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[0].openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                while (line!= null){
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                result = new JSONArray(builder.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection !=null){
                    urlConnection.disconnect();
                }
            }
            return result;
        }
    }
}

