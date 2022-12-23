package com.example.myapplication.ui.dashboard;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class ResultConnection implements Connection {
    private static final String JSON_URL = "http://123.205.91.234:8080/db/findAll";

    public String connect() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body =new FormBody.Builder()
                .add("account","test@gmail.com")
                .add("message","2022-11-28")
                .build();
        Request request = new Request.Builder()
                .url(JSON_URL)
                .method("POST",body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Log.e("a",body.toString());
        return response.toString();
    }
}
