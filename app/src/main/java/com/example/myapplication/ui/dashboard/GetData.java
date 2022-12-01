package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class GetData extends AsyncTask<String, String, String> {
    List<String> resultList = new ArrayList<>();
    String date,year,month,result;
    int day;
    private static final String JSON_URL = "http://172.20.10.4:3000/api/courses";
    @Override
    protected String doInBackground(String... string){
        StringBuilder current = new StringBuilder();
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(JSON_URL);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                int data = isr.read();
                while (data != -1) {
                    current.append((char) data);
                    data = isr.read();

                }
                return current.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return current.toString();
    }
    @Override
    protected void onPostExecute(String s){
        try{
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("parkinson");

            for(int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                date = jsonObject1.getString("date");
                String[] parts = date.split("/");
                year = parts[0];
                month = parts[1];
                day = Integer.parseInt(parts[2]);
                resultList.add(jsonObject1.getString("result"));
                String d = year+"/"+month+"/"+day;
                //Log.d("date",dateString);
                Log.d("dated",d);
                //Log.d("dateString",dateString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public ResultMemento saveToMemento() {
        System.out.println("Saving to Memento.");
        return new ResultMemento(resultList);
    }
}
