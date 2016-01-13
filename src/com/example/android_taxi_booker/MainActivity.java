package com.example.android_taxi_booker;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.github.taxibooker.TaxiBookerHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//static final LatLng Kiev = new LatLng(50.45 , 30.52);
	//private GoogleMap googleMap;
	HashMap order;
	
	 @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);
	      Button buttonOk = (Button)findViewById(R.id.buttonOrder);
	      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	      buttonOk.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	             String textFrom = ((EditText) findViewById(R.id.inputFrom)).getText().toString();	             	             
	             String textTo = ((EditText) findViewById(R.id.inputTo)).getText().toString();
	             //String time = ((EditText) findViewById(R.id.editTime)).getText().toString();
	             String time = new Date(System.currentTimeMillis() + 60 * 1000).toString();
	             String phone = ((EditText) findViewById(R.id.editPhone)).getText().toString();
	             // add initial order
	             HttpRequestSender task = new HttpRequestSender(textFrom, textTo, time, phone);
	 		     task.execute(new String[] { "" });
	             
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
	   
	   private class HttpRequestSender extends AsyncTask<String, Void, String> {
		   
		   String textFrom;	             	             
           String textTo;
           String time;
           String phone;
		   
		   public HttpRequestSender(String textFrom, String textTo, String time, String phone){
			   this.textFrom=textFrom;
			   this.textTo=textTo;
			   this.time=time;
			   this.phone=phone;
		   }
		   
		    @Override
		    protected String doInBackground(String... urls) {
		      String response = "";
		        try {
		        	 //String phone = "8 050 856 13 77";
		             TaxiBookerHttpClient client = new TaxiBookerHttpClient(phone);
		             //String textFrom = "Проспект Победы 45, Киев";
		             //String textTo = "Проулок Ковальский 5, Киев";
		             //String time = new Date(System.currentTimeMillis() + 60 * 1000).toString();
		             Log.d("MyApp",time);
		             String orderId = client.addOrder(textFrom, textTo, time);

		             // get information about the order
		             order = client.getOrder(orderId);
		             Log.d("MyApp","Fetched order: \n" + order);
		             response=order.toString();

		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      return response;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    	 //Log.d("MyApp","Fetched order: \n" + result);
		    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
			      alertDialogBuilder.setMessage(order.toString());
			      
			      alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			         @Override
			         public void onClick(DialogInterface arg0, int arg1) {
			            //Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
			        	 //finish();
			         }
			      });
			      
			      AlertDialog alertDialog = alertDialogBuilder.create();
			      alertDialog.show();
		    }
		  }
	   
//	   public void open(View view){
//		      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//		      alertDialogBuilder.setMessage(order.toString());
//		      
//		      alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//		         @Override
//		         public void onClick(DialogInterface arg0, int arg1) {
//		            Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
//		         }
//		      });
//		      
//		      AlertDialog alertDialog = alertDialogBuilder.create();
//		      alertDialog.show();
//		   }
}
