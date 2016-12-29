package com.example.nickshevr.tmps_rss_lab5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import  com.example.nickshevr.tmps_rss_lab5.RssItem;
import  com.example.nickshevr.tmps_rss_lab5.ListListener;
import  com.example.nickshevr.tmps_rss_lab5.RssReader;
import java.util.List;
import android.os.AsyncTask;

public class MainActivity extends Activity {
    /**
     * This method creates main application view
     */
    private MainActivity local;

    public class UrlsStorage {

        private String rssUrl;

        public String getRssUrl() {
            if (this.rssUrl.length() != 0) {
                return this.rssUrl;
            }

            return "http://news.yandex.ru/politics.rss";
        }

        public void setRssUrl(String inputUrl) {
           this.rssUrl = inputUrl;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.activity_main);

        final UrlsStorage urlsObject = new UrlsStorage();

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute(urlsObject.getRssUrl());

        // Debug the thread name
        Log.d("Thread", Thread.currentThread().getName());
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("Thread", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("Thread", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            ListView itcItems = (ListView) findViewById(R.id.listMainView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);

            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}