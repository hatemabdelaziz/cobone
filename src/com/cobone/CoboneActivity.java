package com.cobone;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CoboneActivity extends Activity implements OnItemSelectedListener{
	Button searchDealsBtn;
	Button siteBtn;
	Button cityBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
//			Toast.makeText(CoboneActivity.this, " we search to you  about latest deals :)",
//					Toast.LENGTH_SHORT).show();
		 setContentView(R.layout.main);
		 final Spinner siteSpinner = (Spinner) findViewById(R.id.siteSpinner);
		    ArrayAdapter<CharSequence> siteadapter = ArrayAdapter.createFromResource(
		            this, R.array.sities_array, android.R.layout.simple_spinner_item);
		    siteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    siteSpinner.setAdapter(siteadapter);
		 final Spinner citySpinner = (Spinner) findViewById(R.id.citiySpinner);
		    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
		            this, R.array.cities_array, android.R.layout.simple_spinner_item);
		    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    citySpinner.setAdapter(cityAdapter);
		    citySpinner.setOnItemSelectedListener(CoboneActivity.this);
		    siteSpinner.setOnItemSelectedListener(CoboneActivity.this);
		   
		    searchDealsBtn = (Button) findViewById(R.id.searchdeals);
		    siteBtn = (Button) findViewById(R.id.sitebtn);
		    cityBtn = (Button) findViewById(R.id.citybtn);
		    
		    siteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                   // TODO Auto-generated method stub
                	siteSpinner.performClick();
               }
           });
		    
		    cityBtn.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                   // TODO Auto-generated method stub
                	citySpinner.performClick();
               }
           });
		    
		    searchDealsBtn.setOnClickListener(new OnClickListener() {		 			
	 			@Override
	 			public void onClick(View v) {
	 				Toast.makeText(CoboneActivity.this, " we search to you  about latest deals :)",
					Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(CoboneActivity.this, AndroidList.class);
					startActivity(intent);  
				} 
			});
		 
		    

	}
	  public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
		  SharedPreferences sharedPreferences=getSharedPreferences("MYPRef", 0);
		  SharedPreferences.Editor editor=sharedPreferences.edit();
		  if(parent.getId() == R.id.citiySpinner){
			  editor.putString("cityName", parent.getItemAtPosition(pos).toString());
			  cityBtn.setText(parent.getItemAtPosition(pos).toString());
//		     Toast.makeText(parent.getContext(),"The city is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		  }
		  else{
			     editor.putString("siteName", parent.getItemAtPosition(pos).toString());
			     siteBtn.setText(parent.getItemAtPosition(pos).toString());
//			     Toast.makeText(parent.getContext(),"The site is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
  
		  }
		  editor.commit();
		 }

        public void onNothingSelected(AdapterView parent) {}
//	public void onClick(View v)
//    {
//		Toast.makeText(CoboneActivity.this,
//				"we search to you  about latest deals :)", Toast.LENGTH_SHORT)
//				.show();
//		Intent intent = new Intent(CoboneActivity.this, AndroidList.class);
//		// Intent intent = new Intent(CoboneActivity.this,
//		// DealActivity.class);
//
//		startActivity(intent);   
//    }
	
//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//		super.onWindowFocusChanged(hasFocus);
//
//		if (hasFocus) {
//			Toast.makeText(CoboneActivity.this, "we search to you  about latest deals :)",
//					Toast.LENGTH_SHORT).show();
//			Intent intent = new Intent(CoboneActivity.this,
//					AndroidList.class);
////			Intent intent = new Intent(CoboneActivity.this,
////					DealActivity.class);
//			
//			startActivity(intent);
//		} else {
//
//		}
//	}
//		

	

}
