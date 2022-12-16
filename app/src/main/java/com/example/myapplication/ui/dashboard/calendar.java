package com.example.myapplication.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class calendar extends AppCompatActivity {

    ResultMemento m = null;
    CalendarView calendarView;
    TextView myDate;
    Button confirm;
    String selectDate;
    ResultCareTaker resultCareTaker = new ResultCareTaker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_dashboard);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);
        confirm = (Button) findViewById(R.id.button);
        Calendar calendar = Calendar.getInstance();
        String thisMonth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String thisYear = String.valueOf(calendar.get(Calendar.YEAR));
        String today = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        selectDate = thisYear + "/" + thisMonth + "/" + today;
        myDate.setText(selectDate);

        //Log.d("OuOb", resultMementoList.toString());
        confirm.setOnClickListener(v -> {
            if(!resultCareTaker.hasMemento(selectDate)) {
                new GetResultData(new ResultConnection()).execute();
                Log.d("OuOb", "getdata");
            } else {
                ResultMemento memento = resultCareTaker.getMemento(selectDate);
                Intent intent = new Intent(calendar.this, watchHistory.class);
                intent.putExtra("date", selectDate);
                intent.putExtra("result", memento.getSavedResult());
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectDate = year+"/"+(month+1)+"/"+dayOfMonth;
            myDate.setText(selectDate);
        });
    }

    private class GetResultData extends GetData {
        String result = "沒有紀錄";
        String date, year, month;
        int day;
        public GetResultData(Connection c) {
            super(c);
        }

        @Override
        protected void onPostExecute(String s) {
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
                    String d = year + "/" + month + "/" + day;
                    if(selectDate.equals(d)) {
                        result = jsonObject1.getString("result");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            resultCareTaker.addMemento(selectDate, new ResultMemento(result));
            ResultMemento memento = resultCareTaker.getMemento(selectDate);
            Intent intent = new Intent(calendar.this, watchHistory.class);
            intent.putExtra("date", selectDate);
            intent.putExtra("result", memento.getSavedResult());
            startActivity(intent);
        }
    }
}