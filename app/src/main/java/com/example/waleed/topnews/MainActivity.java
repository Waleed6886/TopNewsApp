package com.example.waleed.topnews;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {
    ProgressDialog pd;
    NewsAdapter newsAdapter;
    //?api-key=test
    private static final String NEWS_URL = "http://content.guardianapis.com/search";
    // Tag for log messages
    private static final String LOG_TAG = MainActivity.class.getName();
    //http://content.guardianapis.com/search?q=sport%20AND%20education%20AND%20Business&api-key=test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        //Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_books);
        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                News currentNews = newsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                Log.i("uri", websiteIntent.toString());
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage(getString(R.string.loading));
        pd.show();
        Uri uri = Uri.parse(NEWS_URL);
        Uri.Builder uriBuilder = uri.buildUpon();
        //http://content.guardianapis.com/search?q=sport%20AND%20education%20AND%20Business&api-key=test
        uriBuilder.appendQueryParameter("q", "sport%20AND%20education%20AND%20Business");
        uriBuilder.appendQueryParameter("api-key", "test");
        uri = uriBuilder.build();

        Log.d("uri", uri.toString());

        return new NewsLoader(this, uri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        pd.dismiss();
        if (isOnline() == false) {
            Toast.makeText(MainActivity.this,
                    "No internet available", Toast.LENGTH_LONG).show();
        }
        if (data == null && isOnline() == true) {
            Toast.makeText(MainActivity.this,
                    "No News Available", Toast.LENGTH_LONG).show();
            return;
        }
        newsAdapter.setNews(data);

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;

    }
}
