package com.example.footballtardsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.appengers.footballtardsapp.R;

public class ConnectionCheck extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
		super.onCreate(savedInstanceState);
	setContentView(R.layout.internetcheck1);	
	}
	
	  public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	       MenuInflater mi=  getMenuInflater();
	       mi.inflate(R.menu.menu11, menu); 
	       return true;
	    }

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		
			
			
			switch(item.getItemId()){	
		case R.id.refresh:
			boolean a=isNetworkAvailable();
			if(a==true)
			{
			Intent openStartingPoint=new Intent("android.intent.action.RSSFEED");
			startActivity(openStartingPoint);
			}
			else
			{
				Intent openStartingPoint1=new Intent("android.intent.action.CONNECTIONCHECK");
				startActivity(openStartingPoint1);
			}
				break;
				
		}
		return false;
		}
		private boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}
}

