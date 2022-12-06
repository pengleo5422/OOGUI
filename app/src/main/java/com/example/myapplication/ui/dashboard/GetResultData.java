package com.example.myapplication.ui.dashboard;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetResultData extends GetData {
    List<String> resultList = new ArrayList<>();
    public GetResultData(Json j) {
        super(j);
    }
    @Override
    protected void onPostExecute(String s){
        List<String> resultList = new ArrayList<>();
        String date, year, month;
        int day;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("parkinson");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                date = jsonObject1.getString("date");
                String[] parts = date.split("/");
                year = parts[0];
                month = parts[1];
                day = Integer.parseInt(parts[2]);
                resultList.add(jsonObject1.getString("result"));
                String d = year + "/" + month + "/" + day;
                Log.d("dated", d);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ResultMemento saveToMemento() {
        return new ResultMemento(resultList);
    }
}
