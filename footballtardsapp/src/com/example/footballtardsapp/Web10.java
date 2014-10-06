package com.example.footballtardsapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.appengers.footballtardsapp.R;
import com.example.footballtardsapp.*;
public class Web10 extends Activity implements OnClickListener{

	WebView ourBrow;
	TextView textview;
	  String text;
	  ImageView share1;
	  ImageView share2;
	String uri1=ListListener.uri.toString();
	
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web5);
		ourBrow = (WebView) findViewById(R.id.webView11);
		
		share1=(ImageView)findViewById(R.id.image10);
		share2=(ImageView)findViewById(R.id.image11);
        ourBrow.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)   
            {
           
            	
            //showToastMessage.show();
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
        ourBrow.loadUrl(uri1.toString());
        share1.setOnClickListener(this);
        share2.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.image10:
			//create the send intent  
			Intent shareIntent =   
			 new Intent(android.content.Intent.ACTION_SEND);  
			  
			//set the type  
			shareIntent.setType("text/plain");  
			  
			//add a subject  
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   
			 ListListener.x.toString());  
			  
			//build the body of the message to be shared  
			String shareMessage = "Check out this article: "+ uri1.toString();  
			  
			//add the message  
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,   
			 shareMessage);  
			  
			//start the chooser for sharing  
			startActivity(Intent.createChooser(shareIntent,   
			 "Share the webpage via"));  
			break;
		case R.id.image11:
			//create the send intent  
			Intent i=new Intent("android.intent.action.APPACTIVITY");
			startActivity(i);
			break;
	}
	}
	protected void onPause(){
		super.onPause();
		finish();
	}
	
	}