package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

@SuppressLint("StaticFieldLeak")
public abstract class GetData extends AsyncTask<String, String, String> {
    Connection connection;
    public GetData(Connection j) {
        connection = j;
    }
    @Override
    protected String doInBackground(String... strings) {
        return connection.connect();
    }
}
