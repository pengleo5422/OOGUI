package com.example.myapplication.ui.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class json1 implements Json {
    private static final String TAG = "MainActivity";
    private static final String JSON_URL = "http://172.20.10.4:3000/api/courses";

    public String connect() {
        String current = "";
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
                    current = current.concat(String.valueOf((char) data));
                    data = isr.read();

                }
                return current;

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
        return current;
    }
}
