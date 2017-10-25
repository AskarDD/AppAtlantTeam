package ru.askar.appatlantteam.loaders;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.Response;
import ru.askar.appatlantteam.manager.NetworkManager;
import ru.askar.appatlantteam.models.Post;

/**
 * Created by Сайида on 23.10.2017.
 */

public class LoadItem extends AsyncTaskLoader<String> {
    Context context;
    String uri;
    public LoadItem(Context context, String uri) {
        super(context);
        this.context = context;
        this.uri = uri;
    }

    @Override
    public String loadInBackground() {
        Response response = NetworkManager.connection(uri);
        if (response == null) {
            return null;
        }
        if (response.isSuccessful()){
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
