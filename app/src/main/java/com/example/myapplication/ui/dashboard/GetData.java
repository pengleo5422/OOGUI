package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

@SuppressLint("StaticFieldLeak")
public abstract class GetData extends AsyncTask<Void, String, String> {
    Connection connection;
    public GetData(Connection j) {
        connection = j;
    }
    @Override
    protected String doInBackground(Void... params) {
        return connection.connect();
    }
}
