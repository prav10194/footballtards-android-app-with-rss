package com.example.footballtardsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.appengers.footballtardsapp.R;

public class Backgroundimage extends Activity{


	private ProgressBar probar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.backgroundimage);
	
		probar=(ProgressBar) findViewById(R.id.progressBar1);
		probar.setMax(100);
		
		
		Thread timer1=new Thread(){
			public void run(){
				for(int i=0;i<100;i++)
				{
				try{
					sleep(25);
				probar.setProgress(i);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			}
		};
		timer1.start();
		
		Thread timer=new Thread(){
		public void run(){
			
			try{
				sleep(5000);
				
			} 
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			finally{
				
				boolean a=isNetworkAvailable();
				if(a==true)
				{
				Intent openStartingPoint=new Intent("android.intent.action.STARTINGPOINT");
				startActivity(openStartingPoint);
				}
				else
				{
					Intent openStartingPoint1=new Intent("android.intent.action.INTERNETCHECK");
					startActivity(openStartingPoint1);
				}
			}
		}
	};
	timer.start();
	}
	
@Override
protected void onPause(){
	super.onPause();
	finish();
}

private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager 
          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}

}
