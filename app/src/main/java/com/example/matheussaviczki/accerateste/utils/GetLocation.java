package com.example.matheussaviczki.accerateste.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Matheus Saviczki on 09/10/2017.
 */

public class GetLocation implements LocationListener {

    Context context;

    public GetLocation(Context context)
    {
        this.context = context;
    }

    public String TranslateLocation(Location loc)
    {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            String city = addresses.get(0).getLocality();
            return city;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Location getLocation(){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return null;
        }

        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gpsEnabled)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            return loc;
        }
        else
        {
            return  null;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
