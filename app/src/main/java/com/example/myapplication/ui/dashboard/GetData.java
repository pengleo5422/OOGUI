package com.example.myapplication.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

@SuppressLint("StaticFieldLeak")
public abstract class GetData extends AsyncTask<Void, String, String> {
    Connection connection;
    public GetData(Connection j) {
        connection = j;
    }
    @Override
    protected String doInBackground(Void... params) {
        try {
            return connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
