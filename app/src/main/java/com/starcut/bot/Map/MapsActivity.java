package com.starcut.bot.Map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.starcut.bot.R;

import java.io.IOException;
import java.util.List;

import static android.R.id.list;
import static com.starcut.bot.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationmanager;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.addLogAdapter(new AndroidLogAdapter());


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling



            Logger.e("GPS not enabled");

            Context context = getApplicationContext();
            String text = getResources().getString(R.string.location_permission_not_given);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            return;
        }

        LocationListener listner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                processLocation(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, listner);
        }
        else if (locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listner);
        }
        else {
            Logger.e("GPS not enabled");
        }




    }


    void processLocation(Location location)
    {

        if (location == null) {
            Logger.e("location can not be nil");
            return;
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng coordinates = new LatLng(latitude, longitude);
    }

    void addMarker(LatLng coordinates)
    {
        if (coordinates == null)
        {
            return;
        }
        String title = "";

        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1);
            if (addressList.size() > 0) {
                if (addressList.get(0).getLocality() != null) {
                    title = addressList.get(0).getLocality();
                }
                if (addressList.get(0).getCountryName() != null) {
                    title = String.format("%s, %s", title, addressList.get(0).getCountryName());
                }
            }
            else
            {
                title = getResources().getString(R.string.marker_no_information);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (marker == null) {

            Logger.i("marker == null");

            marker = mMap.addMarker(new MarkerOptions().position(coordinates).title(title));
        }
        else {

            Logger.i("marker != null");

            marker.setPosition(coordinates);
            marker.setTitle(title);
        }

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                addMarker(latLng);
            }
        });

        LatLng sydney = new LatLng(-34, 151);
        addMarker(sydney);
    }

}
