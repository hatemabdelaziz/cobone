package com.cobone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CoboneActivity extends Activity implements OnItemSelectedListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

			Toast.makeText(CoboneActivity.this, " we search to you  about latest deals :)",
					Toast.LENGTH_SHORT).show();
		 setContentView(R.layout.main);
		 Spinner siteSpinner = (Spinner) findViewById(R.id.siteSpinner);
		    ArrayAdapter<CharSequence> siteadapter = ArrayAdapter.createFromResource(
		            this, R.array.sities_array, android.R.layout.simple_spinner_item);
		    siteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    siteSpinner.setAdapter(siteadapter);
		 Spinner citySpinner = (Spinner) findViewById(R.id.citiySpinner);
		    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
		            this, R.array.cities_array, android.R.layout.simple_spinner_item);
		    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    citySpinner.setAdapter(cityAdapter);
		    citySpinner.setOnItemSelectedListener(CoboneActivity.this);
		    siteSpinner.setOnItemSelectedListener(CoboneActivity.this);

	}
	  public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
          Toast.makeText(parent.getContext(), "The planet is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
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
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			Toast.makeText(CoboneActivity.this, "we search to you  about latest deals :)",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(CoboneActivity.this,
					AndroidList.class);
//			Intent intent = new Intent(CoboneActivity.this,
//					DealActivity.class);
			
			startActivity(intent);
		} else {

		}
	}
		

	

}
