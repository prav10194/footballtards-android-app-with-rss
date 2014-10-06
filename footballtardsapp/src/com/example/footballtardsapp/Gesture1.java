package com.example.footballtardsapp;

import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.appengers.footballtardsapp.R;

public class Gesture1 extends Activity implements OnClickListener {

	Button b1;
   TextView t2;
    GestureLibrary gesturelib;
    GestureOverlayView gestureOverlayView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website1);
   gestureOverlayView=(GestureOverlayView) findViewById(R.id.wand_catcher);
   gestureOverlayView.addOnGesturePerformedListener(new MagicGestureListener());
   b1=(Button)findViewById(R.id.button1);
   b1.setOnClickListener(this);
   t2=(TextView)findViewById(R.id.textView2);
   gesturelib=GestureLibraries.fromRawResource(this, R.raw.gestures);
	if(!gesturelib.load()){
		
	}
	}
	private class MagicGestureListener implements OnGesturePerformedListener{

		@Override
		public void onGesturePerformed(GestureOverlayView overlay,
				Gesture gesture) {
			// TODO Auto-generated method stub
			boolean spellWorked=false;
			ArrayList<Prediction> predictions=gesturelib.recognize(gesture);
			int predictionSize=predictions.size();
			for(int i=0;i<predictionSize && !spellWorked;i++){
				Prediction prediction=predictions.get(i);
				Log.d("prediction","prediction for" + prediction.name+"is"+prediction.score);
				if(prediction.score>1){
					spellWorked=true;
					String x=prediction.name;
					Intent i1=new Intent(Intent.ACTION_VIEW,Uri.parse("http://the-football-tards.weebly.com/"+x));
					startActivity(i1);
					//magicTextView.setText("You just performed a "+prediction.name);
				}
			}
		
		if(!spellWorked){
			t2.setText("Try Again");
		}
			//magicTextView.setText("Your magic is not working. Try again.");
		}
		}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i=new Intent("android.intent.action.HELPER");
		startActivity(i);
	}
		
	}
	