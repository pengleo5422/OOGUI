package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class watchHistory extends AppCompatActivity {
    private ListView lv;
    String dateString;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.historyresults);
        Intent myIntent = getIntent();
        dateString = myIntent.getStringExtra("date");

        lv = findViewById(R.id.Listview);
        result = myIntent.getStringExtra("result");
        List<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> item = new HashMap<>();
        item.put("result", result);
        list.add(item);


        //Displaying the results
        ListAdapter adapter = new SimpleAdapter(
                watchHistory.this,
                list,
                R.layout.historyresults,
                new String[] {"result"},
                new int[]{R.id.textView});
        lv.setAdapter(adapter);

    }
}