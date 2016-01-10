package com.example.android_taxi_booker;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.github.taxibooker.TaxiBookerHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//static final LatLng Kiev = new LatLng(50.45 , 30.52);
	//private GoogleMap googleMap;
	
	 @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);
	      Button buttonOk = (Button)findViewById(R.id.buttonOrder);
	      buttonOk.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	             EditText textFrom = (EditText) findViewById(R.id.inputFrom);
	             Log.d("MyApp",textFrom.getText().toString());
	             
	             TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

	             // add initial order
	             DownloadWebPageTask task = new DownloadWebPageTask();
	 		     task.execute(new String[] { "http://www.vogella.com" });
	             
//	             try {
//	                 if (googleMap == null) {
//	                    googleMap = ((MapFragment) getFragmentManager().
//	                    findFragmentById(R.id.map)).getMap();
//	                 }
//	                 googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//	                 Marker TP = googleMap.addMarker(new MarkerOptions().
//	                 position(Kiev).title("Kiev"));
//	              }
//	              catch (Exception e) {
//	                 e.printStackTrace();
//	              }
	          }
	       });
	   }
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      getMenuInflater().inflate(R.menu.main, menu);
	      return true;
	   }
	   
	   private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
		    @Override
		    protected String doInBackground(String... urls) {
		      String response = "";
		        try {
		        	String phoneNumber = "8 050 856 13 77";
		             TaxiBookerHttpClient client = new TaxiBookerHttpClient(phoneNumber);
		             String addressFrom = "Проспект Победы 45, Киев";
		             String addressTo = "Проулок Ковальский 5, Киев";
		             String bookingTime = new Date(System.currentTimeMillis() + 60 * 1000).toString();
		             Log.d("MyApp",bookingTime);
		             String orderId = client.addOrder(addressFrom, addressTo, bookingTime);

		             // get information about the order
		             HashMap order = client.getOrder(orderId);
		             Log.d("MyApp","Fetched order: \n" + order);
		             response=order.toString();

		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      return response;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    	 Log.d("MyApp","Fetched order: \n" + result);
		    }
		  }
}
