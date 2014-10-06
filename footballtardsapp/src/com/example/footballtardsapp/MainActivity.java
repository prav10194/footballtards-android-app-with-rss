package com.example.footballtardsapp;

import com.appengers.footballtardsapp.R;
import com.appengers.footballtardsapp.R.menu;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	//final Activity activity = this;
	WebView ourBrow;
	
	 ImageView forw1,exit1,refr1;
	 ImageView rssfeed;
	 ImageView options1;
	
	 private boolean isNetworkAvailable() {
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		
		  if(isAfterInstall())
			{
				popupHelp();
			}
		
		
		forw1=(ImageView)findViewById(R.id.forward);
		exit1=(ImageView)findViewById(R.id.exit);
		refr1=(ImageView)findViewById(R.id.refresh);
		rssfeed=(ImageView)findViewById(R.id.rss1);
		options1=(ImageView)findViewById(R.id.rss11);
		
		forw1.setOnClickListener(this);
		exit1.setOnClickListener(this);
		refr1.setOnClickListener(this);
		rssfeed.setOnClickListener(this);
		options1.setOnClickListener(this);

		
		final Toast showToastMessage=Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT);

		
		final Activity MyActivity = this;
        // Makes Progress bar Visible
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        ourBrow = (WebView) findViewById(R.id.webView1);
        ourBrow.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)   
            {
           
            	
            showToastMessage.show();
           // showToastMessage.cancel();
            
             //Make the bar disappear after URL is loaded, and changes string to Loading...
          //  MyActivity.setTitle("Loading...");
          //  MyActivity.setProgress(progress * 100); //Make the bar disappear after URL is loaded

             // Return the app name after finish loading
               // if(progress == 100)
                 //  x=1;
                	//MyActivity.setTitle(R.string.app_name);
              }
            });
        ourBrow.setWebViewClient(new OurViewClient());
        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.loadUrl("http://footballtards.com");
	
		
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		if ((keyCode==KeyEvent.KEYCODE_BACK) && ourBrow.canGoBack())
			
		{
			ourBrow.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	       MenuInflater mi=  getMenuInflater();
	       mi.inflate(R.menu.cool_menu, menu); 
	       return true;
	    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		
		
		switch(item.getItemId()){	
	case R.id.about:
			Intent i=new Intent("android.intent.action.ABOUT");
			startActivity(i);
			break;
			
	case R.id.rssView:
		boolean a=isNetworkAvailable();
		if(a==false)
		{
		Intent openStartingPoint=new Intent("android.intent.action.INTERNETCHECK");
		startActivity(openStartingPoint);
		}
		
		Intent i4=new Intent("android.intent.action.MAIN100");
		startActivity(i4);
		break;
		
	case R.id.rate:
		Uri uri = Uri.parse("market://details?id=https://play.google.com/store/apps/details?id=com.appengers.footballtardsapp" );
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
		  startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
		  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=https://play.google.com/store/apps/details?id=com.appengers.footballtardsapp")));
		}
		
		break;
		
		case R.id.share:
			//create the send intent  
			Intent shareIntent =   
			 new Intent(android.content.Intent.ACTION_SEND);  
			  
			//set the type  
			shareIntent.setType("text/plain");  
			  
			//add a subject  
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   
			 "The football tards app: ");  
			  
			//build the body of the message to be shared  
			String shareMessage = "https://play.google.com/store/apps/details?id=com.appengers.footballtardsapp"+"\n"+"Download only the RSS app from here: https://play.google.com/store/apps/details?id=com.appengers.rssfootballtards";  
			  
			//add the message  
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,   
			 shareMessage);  
			  
			//start the chooser for sharing  
			startActivity(Intent.createChooser(shareIntent,   
			 "Share the webpage via"));  
			
			break;
			
		case R.id.browser:
			Intent i1=new Intent(Intent.ACTION_VIEW,Uri.parse("http://footballtards.com"));
			startActivity(i1);
			break;
			
		case R.id.help:
			popupHelp();
			break;
			
//		case R.id.gesture:
//			Intent i2=new Intent("android.intent.action.GESTURE");
//			startActivity(i2);
//			break;
			
		case R.id.exit:
		//	String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
		//	 ourBrow.getSettings().setUserAgentString(newUA);
			System.exit(0);
			break;
	}
	return false;
	}
	   
	public boolean dispatchKeyEvent(KeyEvent event) {
	    int action = event.getAction();
	    int keyCode = event.getKeyCode();
	    WebView scrollView = (WebView) findViewById(R.id.webView1);     
	        switch (keyCode) {
	        case KeyEvent.KEYCODE_VOLUME_UP:
	            if (action == KeyEvent.ACTION_DOWN) {
	                scrollView.pageUp(false);   
	            }
	            return true;
	        case KeyEvent.KEYCODE_VOLUME_DOWN:
	            if (action == KeyEvent.ACTION_DOWN) {
	                scrollView.pageDown(false);
	            }
	            return true;
	        default:
	            return super.dispatchKeyEvent(event);
	        }
	    }












	@Override
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.forward:
			//Intent i2=new Intent("android.intent.action.");
			//startActivity(i2);
			
			//System.exit(0);
			//Intent openStartingPoint=new Intent("android.intent.action.STARTINGPOINT");
			//startActivity(openStartingPoint);
			//			if(ourBrow.canGoForward()) {
				//ourBrow.goForward();
				//}
			//ourBrow.goForward();
			//this.openOptionsMenu();
			System.exit(0);
			break;
		case R.id.refresh:
			 
			refr1.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
			exit1.setColorFilter(Color.argb(0,0,0,0));
			
			//System.exit(0);
	//	Intent openStartingPoint=new Intent("android.intent.action.STARTINGPOINT");
	//    startActivity(openStartingPoint);
			
			ourBrow.loadUrl( "javascript:window.location.reload( true )" );
			
			break;
		case R.id.exit:   //back option
			refr1.setColorFilter(Color.argb(0,0,0,0)); 
			exit1.setColorFilter(Color.argb(255, 255, 255, 255));// White Tint
             if(ourBrow.canGoBack())
             {
			 ourBrow.goBack();
             }
			break;
			
		case R.id.rss1:
			
			boolean a=isNetworkAvailable();
			if(a==false)
			{
			Intent openStartingPoint=new Intent("android.intent.action.INTERNETCHECK");
			startActivity(openStartingPoint);
			}
			
			
			
			Intent i4=new Intent("android.intent.action.MAIN100");
			startActivity(i4);
			
			
			
		break;
		
		case R.id.rss11:
			this.openOptionsMenu();
			//System.exit(0);
			break;
		}
		
	} 
	
	private void popup(String title, String text) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage(text)
			   .setTitle(title)
		       .setCancelable(true)
		       .setPositiveButton("ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private boolean isAfterInstall()
	{
	    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
	    boolean ranBefore = preferences.getBoolean("RanBefore", false);
	    if (!ranBefore) {
	        // first time
	        SharedPreferences.Editor editor = preferences.edit();
	        editor.putBoolean("RanBefore", true);
	        editor.commit();
	    }
	    return !ranBefore;
	}
	
	public void popupHelp() {
		// An activity may have been overkill AND for some reason
		// it appears in the task switcher and doesn't allow returning to the 
		// emergency configuration mode. So a dialog is better for this.
		//IntroActivity.open(EmergencyButtonActivity.this);
		
		final String messages [] = {
				"FootballTards app help: (click OK) ",
				"This app contains both the RSS Feeds and the Website View of the blog. To go to RSS Feeds, just touch the button above. Also, you can get a standalone RSS app of the blog on the market by the same developer.",
				"In some android versions, the RSS feeds may be shown as blank. Simply press the BACK button and open the feeds again. For other issues contact the developer on email given in the market. Hope you enjoyed the app. Don't forget to rate and review."
			};
		
		// inverted order - They all popup and you hit "ok" to see the next one.
		popup("3/3", messages[2]);
		popup("2/3", messages[1]);
		popup("1/3", messages[0]);		
	}
	
	
	}
