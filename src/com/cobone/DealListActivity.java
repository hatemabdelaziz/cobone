package com.cobone;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.cobone.JSONfunctions;


import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DealListActivity extends ListActivity {
    /** Called when the activity is first created. */
	
//	ImageView image;
	String image_URL="http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.deallist);
	        ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();
		    String dealValue="";
		    String dealPrice="";
		    String dealCurrency="";
		    
		    
	        JSONObject json = JSONfunctions.getJSONfromURL("http://192.168.1.2:8080/dealAPP/searchdeals.htm");       
	        try{     	
	        	JSONArray  earthquakes = json.getJSONArray("list"); 
//	        	ImageView imgView =(ImageView)findViewById(R.id.imageView1);         
//	        	imgView.setImageDrawable(R.drawable.coboneicon);
	        	for(int i=0;i<earthquakes.length();i++){						
					HashMap<String, Object> map = new HashMap<String, Object>();	
					JSONObject e = earthquakes.getJSONObject(i);
					dealCurrency= e.getString("currency");
					dealPrice=e.getString("price");
					dealValue=e.getString("value");
					map.put("id",  e.getString("id"));
		        	map.put("title",e.getString("title"));
		        	map.put("price",dealPrice+" "+ dealCurrency+" ("+dealValue+" "+ dealCurrency+")");
//		           map.put("photo", imgView);
//		             final LoaderImageView image = (LoaderImageView) findViewById(R.id.loaderImageView);
//	          image.setImageDrawable("http://developer.android.com/images/dialog_custom.png");
//     	        	map.put("photo", R.drawable.icon);	        	
		        	mylist.add(map);			
				}		
	        }catch(JSONException e)        {
	        	 Log.e("log_tag", "Error parsing data "+e.toString());
	        }
	        
//	        ListView listView = (ListView)findViewById(R.id.android_dlist);
	        ListAdapter  adapter = new SimpleAdapter(this, mylist , R.layout.dealbrief, 
	                        new String[] { "title","price"}, 
	                        new int[] { R.id.item_title,R.id.textView2 });
	       
	        setListAdapter(adapter);
//	       setAdapter(adapter);
//	      setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
	      
	        final ListView lv = getListView();
	        lv.setTextFilterEnabled(true);	
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
	        		@SuppressWarnings("unchecked")
					HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
	        		Toast.makeText(DealListActivity.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_SHORT).show(); 

				}
			});

	    }
	 
}
