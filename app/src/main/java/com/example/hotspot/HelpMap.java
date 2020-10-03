package com.example.hotspot;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class HelpMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final static int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Location currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(new LatLng(-34,151)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(12.697855, 35.121133)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(12.697855, 35.121133)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(11.223528, 36.177650)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(9.772259, 34.9191523)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(8.890492, 36.723971)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(5.356011, 25.092326)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(0.817261, 25.263219)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.623754, 19.032479)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-22.349951, 33.270760)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-7.972065, -47.237052)).
                title("Help there is a fire here").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));


        if (currentLocation!=null)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),5));
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    if (mapFragment != null) {
                        mapFragment.getMapAsync(HelpMap.this);
                    }
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation();
            }
        }
    }
}