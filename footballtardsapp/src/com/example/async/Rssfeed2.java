package com.example.async;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.appengers.footballtardsapp.R;
import com.example.rssfiles.ListListener;

import com.example.rssfiles.*;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Rssfeed2 extends Activity {

	private ProgressDialog progressDialog;
	String stringToSearch = "";
	 String nohtml;
	 String stringToSearch2="";
	 String nohtml2;
	 String stringToSearch4="";
	 String nohtml4;
	 
	   Drawable drawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		new LoadViewTask().execute();  
		
	   
	//	try {
			//t1.setText(getURLtext("http://stackoverflow.com/questions/12759336/android-get-web-page-source"));
	//	} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	}
	
	public void getContent()
	{
		
//	t1.setText(stringToSearch);
	    // the pattern we want to search for
	    Pattern p = Pattern.compile("<div id=\"55\">(.+?)</div>");
	    Matcher m = p.matcher(stringToSearch2);
	 
	    // if we find a match, get the group 
	    while (m.find())
	    {
	      // get the matching group
	      String codeGroup = m.group(1);
	       
	      // print the group
	      String x=codeGroup;
	      nohtml2 = x.toString().replaceAll("\\<.*?>","");
	   //  t1.setText(nohtml);
	    }
	}

	public void getTitle1()
	{
		
	//t2.setText(stringToSearch);
	    // the pattern we want to search for
	    Pattern p = Pattern.compile("<title>(.+?)</title>");
	    Matcher m = p.matcher(stringToSearch);
	 
	    // if we find a match, get the group 
	    while (m.find())
	    {
	      // get the matching group
	      String codeGroup = m.group(1);
	       
	      // print the group
	      String x=codeGroup;
	      nohtml= x.toString().replaceAll("\\<.*?>","");
	  //   t2.setText(nohtml);
	    }
	}
	
	public void getImage()
	{
		
		
		String url1=getUrl();
		
		// ImageView imgView =(ImageView)findViewById(R.id.imageView1);
          drawable = LoadImageFromWebOperations(url1);
         // imgView.setImageDrawable(drawable);

	}

	 private Drawable LoadImageFromWebOperations(String url)
	    {
	          try{
	        InputStream is = (InputStream) new URL(url).getContent();
	        Drawable d = Drawable.createFromStream(is, "src name");
	        return d;
	      }catch (Exception e) {
	        System.out.println("Exc="+e);
	        return null;
	      }
	    }
	
	 public String getUrl()
	 {
		
		 String url1;
			
		url1=stringToSearch4;
		    // the pattern we want to search for
		    Pattern p = Pattern.compile("<img class=\"alignleft\" src=\"(.+?)\"");
		    Matcher m = p.matcher(stringToSearch4);
		 
		    // if we find a match, get the group 
		    while (m.find())
		    {
		      // get the matching group
		      String codeGroup = m.group(1);
		       
		      // print the group
		      String x=codeGroup;
		     
		      nohtml4 = x.toString().replaceAll("\\<.*?>","");
		     
		     url1=nohtml4;
		    }
		    return url1;
	 }
	
	public String getURLtext(String zielurl) throws IllegalStateException, IOException

	{
	    String result = ""; // default empty string
	    try
	    {
	        String meineurl = zielurl;

	    HttpClient httpClient = new DefaultHttpClient();
	    HttpContext localContext = new BasicHttpContext();
	    HttpGet httpGet = new HttpGet(meineurl);
	    HttpResponse response = httpClient.execute(httpGet, localContext);

	    InputStream is = response.getEntity().getContent();
	    result = inputStreamToString(is).toString();
	    }
	    catch (Exception ex)
	    {
	       // do some Log.e here
	    }
	        return result;          
	    
	}

	// Fast Implementation
	private StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();

	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
	        while ((line = rd.readLine()) != null) { 
	            total.append(line); 
	        }
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    // Return full string
	    return total;
	}

	
	  private class LoadViewTask extends AsyncTask<Void, Integer, Void>
	    {
	    	//Before running code in the separate thread
			@Override
			protected void onPreExecute() 
			{
				setContentView(R.layout.layout1);
				
				progressDialog = ProgressDialog.show(Rssfeed2.this,"Loading...",  
					    "Loading application View, please wait...", false, false);  
				
			}
			
			//The code to be executed in a background thread.
			@Override
			protected Void doInBackground(Void... params) 
			{
				/* This is just a code that delays the thread execution 4 times, 
				 * during 850 milliseconds and updates the current progress. This 
				 * is where the code that is going to be executed on a background
				 * thread must be placed. 
				 */
				 String uri1=ListListener.uri.toString();
				try {
					stringToSearch4 = getURLtext(uri1);
					stringToSearch2 = getURLtext(uri1);
					stringToSearch = getURLtext(uri1);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				getTitle1();
				getContent();
			
				
				try 
				{
					
					//Get the current thread's token
					synchronized (this) 
					{
						
						//Initialize an integer (that will act as a counter) to zero
						int counter = 0;
						//While the counter is smaller than four
						while(counter <= 2)
						{
							//Wait 850 milliseconds
							this.wait(1);
							//Increment the counter 
							counter++;
							//Set the current progress. 
							//This value is going to be passed to the onProgressUpdate() method.
							publishProgress(counter*25);
						}
					}
					
					
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				return null;
			}

			//Update the progress
			@Override
			protected void onProgressUpdate(Integer... values) 
			{
				
				//set the current progress of the progress dialog
				progressDialog.setProgress(values[0]);
			}

			//after executing the code in the thread
			@Override
			protected void onPostExecute(Void result) 
			{
			
				getImage();
				//close the progress dialog
				progressDialog.dismiss();
				//initialize the View
		
				TextView t2=(TextView)findViewById(R.id.textView2);
				t2.setText(stringToSearch);
			 t2.setText(nohtml);
			 
				TextView t1=(TextView)findViewById(R.id.textView1);
				t1.setText(stringToSearch2);
			    t1.setText(nohtml2);
			    
			    ImageView imgView =(ImageView)findViewById(R.id.imageView1);
				
		imgView.setImageDrawable(drawable);
		
		
		


			ImageView browser=(ImageView)findViewById(R.id.browser1);
			browser.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent i1=new Intent(Intent.ACTION_VIEW,Uri.parse(ListListener.uri.toString()));
					startActivity(i1);
				}
			});
			
			ImageView share=(ImageView)findViewById(R.id.share1);
			share.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					Intent shareIntent =   
							 new Intent(android.content.Intent.ACTION_SEND);  
							  
							//set the type  
							shareIntent.setType("text/plain");  
							  
							//add a subject  
							shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   
							 "Check out: "+nohtml);  
							  
							//build the body of the message to be shared  
							String shareMessage = "@footballtards: "+ListListener.uri.toString();  
							  
							//add the message  
							shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,   
							 shareMessage);  
							  
							//start the chooser for sharing  
							startActivity(Intent.createChooser(shareIntent,   
							 "Share the content via"));  
					
				}
			});
			
			ImageView copy=(ImageView)findViewById(R.id.copy1);
			copy.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					clipboard.setText(nohtml+"\n"+nohtml2);
		            Toast.makeText(getApplicationContext(), "Text Copied To Clipboard", Toast.LENGTH_SHORT).show();
					
				}
			});
			
			
		
		
			} 	
	    }

	
	
}




