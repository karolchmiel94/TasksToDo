package com.example.karol.reminder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tagmanager.TagManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationListener locManager;
    Button searchButton;
    EditText enterLocation;

    //------------------------------------------TO DO-----------------------------------------------
    //                                     Reverse geocoding!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchButton = (Button) findViewById(R.id.search_localization);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = enterLocation.getText().toString();
                Toast.makeText(getApplicationContext(), "Searching for " + text, Toast.LENGTH_LONG).show();
            }
        });

        enterLocation = (EditText) findViewById(R.id.enter_localization);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Gliwice and move the camera
        LatLng gliwice = new LatLng(50.310, 18.699);
        mMap.addMarker(new MarkerOptions().position(gliwice).title("Marker in Gliwice"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gliwice));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

    @Override
    public void onLocationChanged(Location location) {

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
}
