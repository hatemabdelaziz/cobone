package com.cobone;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class DealActivity extends Activity {
	static final long ONE_HOUR = 60 * 60 * 1000L;
	TextView dealTitle;
	TextView endDateText;
	TextView priceText;
	TextView valText;
	TextView disValText;
	TextView saveText;
	ImageView dealImage;
	Button buyButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.deal);
        final Intent intent = new Intent(Intent.ACTION_VIEW);
		 SharedPreferences sharedPreferences=getSharedPreferences("MYPRef", 0);
		 String Url="http://192.168.1.2:8080/dealAPP/getDeal.htm?id="+sharedPreferences.getString("dealId","");
//	     JSONObject json = JSONfunctions.getJSONfromURL(Url);
	    
//         if(json.length() > 0) {
        	 try {
//				 Toast.makeText(DealActivity.this, json.length()+"e0llllllllll'", Toast.LENGTH_SHORT).show();

//				JSONObject e = (JSONObject) json.getJSONArray("object").get(0);
//				JSONObject b = (JSONObject) json.getJSONArray("object").get(1);
			     JSONObject e1 = JSONfunctions.getJSONfromURL(Url);
			     JSONObject e = e1.getJSONObject("object");
			  
//				 Toast.makeText(DealActivity.this,Url+"e0'" + e.getString("currency")+"+++=e1", Toast.LENGTH_SHORT).show();
       		    Date today = new Date();
       		    Date endDate =new Date (Long.parseLong(e.getString("end")));
       	        long hourdiff = endDate.getTime() - today.getTime();
        	    long hours = (hourdiff + ONE_HOUR) / (ONE_HOUR);
       	        long mintuesdiff = (long) ((((double) (hourdiff + ONE_HOUR) / (ONE_HOUR)) - hours) * 60);
       	         hourdiff = (hourdiff + ONE_HOUR) / (ONE_HOUR);   
				String endDateString=hourdiff+" hrs "+mintuesdiff+" min ";
				dealTitle = (TextView) findViewById(R.id.dealtitle);
				endDateText =(TextView) findViewById(R.id.endvaltext);
				priceText= (TextView) findViewById(R.id.priceText);
				valText =(TextView) findViewById(R.id.valueText);
				disValText = (TextView) findViewById(R.id.disValText);
				saveText =(TextView) findViewById(R.id.saveVal);
				dealImage= (ImageView) findViewById(R.id.dealimage);
				buyButton = (Button) findViewById(R.id.buybutton);
				
				dealTitle.setText(e.getString("title"));
				endDateText.setText(endDateString);
				priceText.setText(String.format("%.0f", Double.parseDouble(e.getString("price")))+" "+e.getString("currency"));
				valText.setText(""+String.format("%.0f", Double.parseDouble(e.getString("value"))));
				disValText.setText(""+String.format("%.0f", Double.parseDouble(e.getString("discount")))+" % ");
				saveText.setText(String.format("%.0f", Double.parseDouble(e.getString("saving"))));
                 Drawable drawable = JSONfunctions.LoadImageFromWebOperations(e.getString("photo"));
                 dealImage.setImageDrawable(drawable);
                 final Uri buyUri = Uri.parse(e.getString("url"));
                 buyButton.setOnClickListener(new OnClickListener() {         

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						try {
                            // Start the activity
                     	 intent.setData(buyUri);
                            startActivity(intent);
                          } catch (ActivityNotFoundException e) {
                            // Raise on activity not found
                            Toast.makeText(DealActivity.this, "Browser not found.", Toast.LENGTH_SHORT);
                          }
					} 
                 });
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//         }
	}
}
