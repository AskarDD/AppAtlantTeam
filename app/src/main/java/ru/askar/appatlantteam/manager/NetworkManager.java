package ru.askar.appatlantteam.manager;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Сайида on 23.10.2017.
 */

public class NetworkManager {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "https://jsonplaceholder.typicode.com";

    public static Response connection(String uri){
        Response response = null;
        Request request = new Request.Builder()
                .url(URL + "/" + uri)
                .get()
                .build();
        OkHttpClient httpClient = new OkHttpClient();
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            Log.i("Ошибка запроса ", e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}
