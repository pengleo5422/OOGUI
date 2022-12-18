package com.example.myapplication.ui.dashboard;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultConnection implements Connection {
    private static final String JSON_URL = "http://192.168.149.117:3000/api/courses";

    public String connect() {
        StringBuilder current = new StringBuilder();
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(JSON_URL);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                int data = isr.read();
                while (data != -1) {
                    current.append((char) data);
                    data = isr.read();
                }
                return current.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return current.toString();
    }
}
