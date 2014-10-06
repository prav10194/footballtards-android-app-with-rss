package rssfiles;

import java.net.URI;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.async.*;
/**
 * Class implements a list listener.
 * @author ITCuties
 */
public class ListListener implements OnItemClickListener {
    // Our listener will contain a reference to the list of RSS Items
    // List item's reference
    List<RssItem> listItems;
    // And a reference to a calling activity
    // Calling activity reference
    Activity activity;
    public static Uri uri;
    static String x;
    /** We will set those references in our constructor.*/
    public ListListener(List<RssItem> aListItems, Activity anActivity) {
        listItems = aListItems;
        activity  = anActivity;
    }
 
    /** Start a browser with url from the rss item.*/
    public void onItemClick(AdapterView parent, View view, int pos, long id) {
    	 uri = Uri.parse(listItems.get(pos).getLink());
    	x=listItems.get(pos).getTitle().toString();
    	Intent intent1 = new Intent("android.intent.action.MAIN2");
 	   activity.startActivity(intent1);
 	 
 	 // UUri.parse(listItems.get(pos).getLink())
    	// We create an Intent which is going to display data
                     //// Intent i = new Intent(Intent.);
        // We have to set data for our new Intent
                    //// i.setData(Uri.parse(listItems.get(pos).getLink()));
        // And start activity with our Intent
                    ////  activity.startActivity(i);
    	// Intent i5 = new Intent(activity, Web2.class);
        // i5.setData(Uri.parse(listItems.get(pos).getContent()));
          
        // i5.putExtra("title", listItems.get(pos).getTitle());
        // i5.putExtra("content", listItems.get(pos).getContent());
          
        // activity.startActivity(i5);
    }
}
//- See more at: http://www.itcuties.com/android/how-to-write-android-rss-parser/#sthash.IkWuq76s.dpuf