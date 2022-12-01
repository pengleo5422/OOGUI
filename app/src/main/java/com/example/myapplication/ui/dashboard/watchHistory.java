package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

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

public class watchHistory extends AppCompatActivity {


    private ListView lv;

    String dateString;
    List<String> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyresults);
        Intent myIntent = getIntent();
        dateString = myIntent.getStringExtra("date");


        lv = findViewById(R.id.Listview);
        resultList = myIntent.getStringArrayListExtra("resultList");
        List<HashMap<String, String>> list = new ArrayList<>();
        for(String result : resultList) {
            HashMap<String, String> item = new HashMap<>();
            item.put("result", result);
        }
        //Displaying the results
        ListAdapter adapter = new SimpleAdapter(
                watchHistory.this,
                list,
                R.layout.historyresults,
                new String[] {"result"},
                new int[]{R.id.textView});
        lv.setAdapter(adapter);
        String str = resultList.toString();
        Log.d("sus",str);
    }
}