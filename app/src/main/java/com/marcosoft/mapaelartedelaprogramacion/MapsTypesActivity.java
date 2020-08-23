package com.marcosoft.mapaelartedelaprogramacion;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsTypesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnTerreno,btnSatelital,btnHibrido,btnNormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_types);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnHibrido = (Button) findViewById(R.id.btnHibrido) ;
        btnSatelital = (Button) findViewById(R.id.btnSatelital) ;
        btnTerreno = (Button) findViewById(R.id.btnTerreno) ;
        btnNormal = (Button) findViewById(R.id.btnNormal) ;
    }

    public void CambiarHibrido (View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    public void CambiarTerreno (View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }
    public void CambiarSatelital (View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    public void CambiarNormal (View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}