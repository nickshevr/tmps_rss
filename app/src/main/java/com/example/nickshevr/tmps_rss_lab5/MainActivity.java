package com.example.nickshevr.tmps_rss_lab5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import  com.example.nickshevr.tmps_rss_lab5.RssItem;
import  com.example.nickshevr.tmps_rss_lab5.ListListener;
import  com.example.nickshevr.tmps_rss_lab5.RssReader;

public class MainActivity extends Activity {
    /**
     * This method creates main application view
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.activity_main);
        try {
            // Create RSS reader
            RssReader rssReader = new RssReader("http://news.yandex.ru/politics.rss");
            // Get a ListView from main view
            ListView itcItems = (ListView) findViewById(R.id.listMainView);
            // Create a list adapter
            ArrayAdapter adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);
            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
        } catch (Exception e) {
            Log.e("ITCRssReader", e.getMessage());
        }
    }
}