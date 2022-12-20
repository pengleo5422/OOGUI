package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
    TextView teh;
    TextView te4;
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.historyresults);
        Intent myIntent = getIntent();
        dateString = myIntent.getStringExtra("date");


        result = myIntent.getStringExtra("result");

        teh = findViewById(R.id.textViewh);

        te4 = findViewById(R.id.textView4);

        if(result.equals("false")){
            te4.setText("");
            teh.setText("無異狀");
            teh.setTextColor(Color.parseColor("#59bb9b"));
        }else {
            teh.setText(result);
            teh.setTextColor(Color.parseColor("#ff8080"));
        }


    }
}