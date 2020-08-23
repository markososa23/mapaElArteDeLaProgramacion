package com.marcosoft.mapaelartedelaprogramacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity1 extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback,GoogleMap.OnMarkerDragListener,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker marcadorPrueba;
    private Marker marcadorDrag,infoWindows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // CIUDAD DE MEXICO
        LatLng mexico = new LatLng(19.43, -99.13);
        mMap.addMarker(new MarkerOptions().position(mexico).draggable(true).title("Ciudad de Mexico").snippet("la pinche ciudad de frijoleros").icon(BitmapDescriptorFactory.fromResource(R.drawable.mexico)));


        // CIUDAD DE bravo
        LatLng valle = new LatLng(19.1950, -99.1332);
        mMap.addMarker(new MarkerOptions().position(valle).title("Ciudad del Bravo").snippet("la pinche ciudad de braveros").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        // CIUDAD DE HERMOSILLO
        LatLng hermosillo = new LatLng(27.72, -107.53);
        mMap.addMarker(new MarkerOptions().position(hermosillo).title("Ciudad de Hermosillo").snippet("la pinche ciudad de hermosillo").icon(BitmapDescriptorFactory.fromResource(R.drawable.hermosillo)));

        // CIUDAD MARCADOR
        LatLng prueba = new LatLng(19.0412,-98.2061);
        marcadorPrueba = googleMap.addMarker(new MarkerOptions()
        .position(prueba)
        .title("Ejemplo"));

        // CIUDAD MORELOS
        LatLng morelos = new LatLng(18.6813,-99.1013);
        marcadorDrag = googleMap.addMarker(new MarkerOptions()
                .position(morelos)
                .title("Ejemplo")
                .draggable(true));

        // CIUDAD toluca
        LatLng toluca = new LatLng(19.2826,-99.6556);
        infoWindows = googleMap.addMarker(new MarkerOptions()
                .position(toluca)
                .title("Toluca")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico,7));
        //click en  marcador
        googleMap.setOnMarkerClickListener(this);
        //arrastrar marcador
        googleMap.setOnMarkerDragListener(this);
        //evento de click en info
        googleMap.setOnInfoWindowClickListener(this);
    }

    @SuppressLint({"WrongConstant"})
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(marcadorPrueba)){
            String varLat,varLong;
            varLat= Double.toString(marcadorPrueba.getPosition().latitude);
            varLong= Double.toString(marcadorPrueba.getPosition().longitude);
            Toast.makeText(this,(varLat + " " + varLong),Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(marcadorDrag)){
            Toast.makeText(this, "Coloque en la zona a reclamar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(marcadorDrag)){
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    if (marker.equals(infoWindows)){
    TolucaFragment.newInstance(marker.getTitle(),getString(R.string.TolucaInfo))
            .show(getSupportFragmentManager(),null);
}
    }
}