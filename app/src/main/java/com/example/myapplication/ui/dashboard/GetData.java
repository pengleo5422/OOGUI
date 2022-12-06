package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

@SuppressLint("StaticFieldLeak")
public abstract class GetData extends AsyncTask<String, String, String> {
    Json json;
    public GetData(Json j) {
        json = j;
    }
    @Override
    protected String doInBackground(String... string){
        return json.connect();
    }
}
