package com.example.myapplication.ui.dashboard;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class GetChartData extends GetData{
    LineData data = null;
    String date, year, month, result;
    int day;
    int week1 = 0;
    int week2 = 0;
    int week3 = 0;
    int week4 = 0;
    int week5 = 0;
    public GetChartData(Json j) {
        super(j);
    }
    @Override
    protected void onPostExecute(String s){
        try {
            Calendar calendar = Calendar.getInstance();
            String thisMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String thisYear = String.valueOf(calendar.get(Calendar.YEAR));
            Log.d("month", thisYear + "/" + thisMonth);

            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("parkinson");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                date = jsonObject1.getString("date");
                String[] parts = date.split("/");
                year = parts[0];
                month = parts[1];
                day = Integer.parseInt(parts[2]);
                result = jsonObject1.getString("result");

                // Hashmap
                if (result.equals("有異狀") && year.equals(thisYear) && month.equals(thisMonth)) {
                    if (1 <= day && day <= 7) {
                        week1 = week1 + 1;
                    } else if (8 <= day && day <= 14) {
                        week2 = week2 + 1;
                    } else if (15 <= day && day <= 21) {
                        week3 = week3 + 1;
                    } else if (22 <= day && day <= 28) {
                        week4 = week4 + 1;
                    } else if (29 <= day) {
                        week5 = week5 + 1;
                    }
                }
            }
            ArrayList<Entry> yValues = new ArrayList<>();
            //int a = 1;
            yValues.add(new Entry(0, week1));
            yValues.add(new Entry(1, week2));
            yValues.add(new Entry(2, week3));
            yValues.add(new Entry(3, week4));
            //yValues.add(new Entry(4,week5));
            LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
            set1.setFillAlpha(110);
            set1.setColor(Color.RED);
            set1.setDrawFilled(true);
            set1.setLineWidth(3f);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            data = new LineData(dataSets);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public LineData getData() {
        return data;
    }
}
