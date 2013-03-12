package com.mywork.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.mywork.ui.R;
import com.samtube405.track.CircleOverlay;


public class MyTours extends MapActivity {

	// Map view
	MapView mapView;

	// Map overlay items
	List<Overlay> mapOverlays;

	

	GeoPoint geoPoint;
	// Map controllers
	MapController mc;

	

	double latitude;
	double longitude;
	OverlayItem overlayitem;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile1);

		// Getting intent data
		Intent i = getIntent();

		// retrieve data from MainActivity
		ArrayList<HashMap<String, String>> toursList = new ArrayList<HashMap<String, String>>();
		toursList = (ArrayList<HashMap<String, String>>) i
				.getSerializableExtra("tour_list");

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();
		
		String user_latitude,user_longitude;
		
		for (HashMap<String, String> map : toursList){
			
			user_latitude= map.get("latitude");
			user_longitude= map.get("longitude");
			
			latitude = Double.parseDouble(user_latitude); // latitude
			longitude = Double.parseDouble(user_longitude); // longitude

			// Geopoint to place on map
			geoPoint = new GeoPoint((int) (latitude * 1E6),
					(int) (longitude * 1E6));

			mapOverlays.add(new CircleOverlay(this, geoPoint, 1000));
			
		}		

		mc = mapView.getController();
		mc.setZoom(25);

		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
