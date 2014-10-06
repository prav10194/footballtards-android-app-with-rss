package com.example.footballtardsapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import com.appengers.footballtardsapp.R;

public class Web2 extends Activity {
	 protected void onCreate(Bundle savedInstanceState) {
		 
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.webview1);
	         
	        String title    = (String)getIntent().getExtras().get("title");
	        String content  = (String)getIntent().getExtras().get("content");
	         
	 
	        Log.d("DEBUG", "title:\t" + title);
	 
	        Log.d("DEBUG", "content:\t\t" + content);
	         
	         
	        TextView titleTV = (TextView)findViewById(R.id.detailsTextView);
	        WebView webView  = (WebView)findViewById(R.id.detailsWebView);
	         
	        titleTV.setText(title);
	        webView.loadData(content, "text/html", "UTF-8");
	         
	}
}
	