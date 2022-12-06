package com.example.myapplication.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class calendar extends AppCompatActivity {
    private HashMap<String, ResultMemento> resultList = new HashMap<>();
    CalendarView calendarView;
    TextView myDate;
    Button confirm;
    String date;
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
        date = thisYear + "/" + thisMonth + "/" + today;
        myDate.setText(date);

        confirm.setOnClickListener(v -> {
            if(!resultList.containsKey(date)) {
                GetResultData getResultData = new GetResultData(new json2());
                getResultData.execute();
                addMemento(date, getResultData.saveToMemento());
            }
            ResultMemento memento = getMemento(date);
            Intent intent = new Intent(calendar.this, watchHistory.class);
            intent.putExtra("date",date);
            intent.putStringArrayListExtra("resultList", (ArrayList<String>)memento.getSavedResultList());
            startActivity(intent);
        });

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            date = year+"/"+(month+1)+"/"+dayOfMonth;
            myDate.setText(date);
        });
    }
    public void addMemento(String date, ResultMemento m) {
        resultList.put(date, m);
    }

    public ResultMemento getMemento(String date) {
        return resultList.get(date);
    }
}