package com.example.test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;


public class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {

	Context mContext;

    public ReverseGeocodingTask(Context context) {
        super();
        mContext = context;
    }

    @SuppressLint("ParserError")
	@Override
    protected Void doInBackground(Location... params) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

        Location loc = params[0];
        List<Address> addresses = null;
        try {
            // Call the synchronous getFromLocation() method by passing in the lat/long values.
            addresses = geocoder.getFromLocation(loc.getLatitude() + 10, loc.getLongitude() + 10, 1);
        } catch (IOException e) {
            e.printStackTrace();
            // Update UI field with the exception.
            Log.e("Error while getting the address", e.getMessage());
        }
        if (addresses != null && addresses.size() > 0) 
        {
            Address address = addresses.get(0);
            // Format the first line of address (if available), city, and country name.
            String addressText = String.format("%s, %s, %s",
                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                    address.getLocality(),
                    address.getCountryName());
            // Update the UI via a message handler.
           Log.d("Current Address", addressText);
        }
        return null;
    }

}
