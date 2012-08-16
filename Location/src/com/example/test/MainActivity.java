package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.google.ads.*;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
    @SuppressLint("ParserError")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        final Context ctx = this;
        
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {

            @SuppressLint("NewApi")
			public void onLocationChanged(Location location) {
                // Bypass reverse-geocoding if the Geocoder service is not available on the
                // device. The isPresent() convenient method is only available on Gingerbread or above.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && Geocoder.isPresent()) {
                    // Since the geocoding API is synchronous and may take a while.  You don't want to lock
                    // up the UI thread.  Invoking reverse geocoding in an AsyncTask.
                    (new ReverseGeocodingTask(ctx)).execute(new Location[] {location});
                }
            }

    		@Override
    		public void onProviderDisabled(String arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void onProviderEnabled(String arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    			// TODO Auto-generated method stub
    			
    		}
        };
        
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        
        AdRequest adRequest = new AdRequest();
        adRequest.addKeyword("Sports");
        adRequest.addTestDevice("28B06A279F3FC7DC328D0BD0AF1C6A13");
        AdView mAdView = (AdView) findViewById(R.id.ad);
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    
    public void sendMessage(View view)
    {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent); 
    }
}
