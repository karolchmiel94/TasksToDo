package com.example.karol.reminder;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationListener locManager;
    Button searchButton, addButton;
    EditText enterLocation;
    private android.location.Address address;
    private String location;
    private String street, city, number;

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
        addButton = (Button) findViewById(R.id.add_localization);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Gliwice and move the camera
        LatLng gliwice = new LatLng(50.310, 18.699);
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

    public void onSearch(View view) {
        enterLocation = (EditText) findViewById(R.id.enter_localization);
        location = enterLocation.getText().toString();
        List<android.location.Address> addressList = null;

        if(location != null || !location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.address = addressList.get(0);
            number = address.getSubThoroughfare();
            city = address.getLocality();
            street = address.getThoroughfare();
            LatLng latLng = new LatLng(this.address.getLatitude(), this.address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            searchButton.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
        }
    }

    public void onAdd(View view) {
        Toast.makeText(getApplicationContext(), "Location of " + location + " is in " + city + ", " +
                street + " " + number, Toast.LENGTH_LONG).show();

        searchButton.setVisibility(View.VISIBLE);
        addButton.setVisibility(View.INVISIBLE);
    }
}
