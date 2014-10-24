package com.mazariolli.android.classattendees.fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mazariolli.android.classattendees.location.LocationHelper;

public class MapFragment extends SupportMapFragment {
	
	@Override
	public void onResume() {
		super.onResume();
		LatLng latLng = LocationHelper.getCoords(getActivity(), "R. Vergueiro, 3185 - Vila Mariana, São Paulo - SP, 04101-300");
		moveCameraTo(latLng);
	}

	private void moveCameraTo(LatLng latLng) {
		CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(latLng, 17);
		getMap().moveCamera(camera);
	}

}
