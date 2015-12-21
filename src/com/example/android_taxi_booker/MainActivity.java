package com.example.android_taxi_booker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
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
}
