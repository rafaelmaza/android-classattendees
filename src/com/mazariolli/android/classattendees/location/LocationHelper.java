package com.mazariolli.android.classattendees.location;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class LocationHelper {

	public static LatLng getCoords(Context ctx, String address) {
		Geocoder geo = new Geocoder(ctx);
		List<Address> addresses = null;
		LatLng latLng = null;
		try {
			addresses = geo.getFromLocationName(address, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(addresses != null && !addresses.isEmpty()) {
			Address locationAddr = addresses.get(0);
			latLng = new LatLng(locationAddr.getLatitude(), locationAddr.getLongitude());
		}
		return latLng;
	}

}
