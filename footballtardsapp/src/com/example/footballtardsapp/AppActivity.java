package com.example.footballtardsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.appengers.footballtardsapp.R;
import com.example.footballtardsapp.*;
 
/**
 * Main application activity.
* @author ITCuties
*/
public class AppActivity extends Activity {
    /**
     * This method creates main application view
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view
        setContentView(R.layout.activity1);
        try {
            // Create RSS reader
            RssReader rssReader = new RssReader("http://footballtards.com/feed/");;
            // Get a ListView from main view
            ListView itcItems = (ListView) findViewById(R.id.listView1);
            // Create a list adapter
            ArrayAdapter adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);
            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
            
        } catch (Exception e) {
            Log.e("ITCRssReader", e.getMessage());
        }
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //- See more at: http://www.itcuties.com/android/how-to-write-android-rss-parser/#sthash.IkWuq76s.dpuf
    }
    
}
//- See more at: http://www.itcuties.com/android/how-to-write-android-rss-parser/#sthash.IkWuq76s.dpuf