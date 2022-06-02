package com.planatechnologies.androidchallenge.Helpers;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIClient {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient client = new OkHttpClient();

    public static String post(String url, String json) throws IOException {
        String _URL = "https://api.m3o.com"+url;
        RequestBody body = RequestBody.create(json,JSON);
        Request request = new Request.Builder()
                .url(_URL)
                .post(body)
                .header("Authorization","Bearer NGRlOWM5ODktNWJkMC00NDUzLWJjYzAtMzllOWM4Y2VhZGQ0")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        return response.body().string();
    }
}
