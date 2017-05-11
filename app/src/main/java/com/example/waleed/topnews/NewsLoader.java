package com.example.waleed.topnews;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Waleed on 10/05/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String loaderUrl;

    public NewsLoader(Context context, String loaderUrl) {
        super(context);
        this.loaderUrl = loaderUrl;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (loaderUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of News.
        List<News> News = QueryUtils.fetchEarthquakeData(loaderUrl);
        return News;
    }

}
