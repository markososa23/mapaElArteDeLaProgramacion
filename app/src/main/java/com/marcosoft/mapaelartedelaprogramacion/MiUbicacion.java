package com.marcosoft.mapaelartedelaprogramacion;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MiUbicacion extends FragmentActivity implements GoogleMap.OnMarkerDragListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private double UltimaLongitud, UltimaLatitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ubicacion);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
   /*     LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        //arrastrar marcador
        googleMap.setOnMarkerDragListener(this);
    }
    private void agregarMarcador(double latitud, double longitud) {
        LatLng coordenadas = new LatLng(latitud, longitud);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).draggable(true).title("Mi Posicion Actual"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
        mMap.animateCamera(miUbicacion);

    }
    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMapsActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 0,Local);
        //mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 0, Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, Local);
        ///mensaje1.setText("Localizacion agregada");
        ///mensaje2.setText("");
    }
    //AL PRESIONAR EL BOTON UBICAR AGREGA MARCADOR EN LA ULTIMA LAT LONG
    public void orientarEnMapa (View v){
    agregarMarcador(UltimaLatitud,UltimaLongitud);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(marcador)){
            Toast.makeText(this, "Coloque en la zona a reclamar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(marcador)){
            String ubicacionMarcador = marker.getPosition().latitude+" "+marker.getPosition().longitude;
   /*         String ubicacionMarcador = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);*/
            Toast.makeText(this, ubicacionMarcador, Toast.LENGTH_SHORT).show();
            //setTitle(ubicacionMarcador);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        MiUbicacion mainActivity;
        public MiUbicacion getMainActivity() {
            return mainActivity;
        }
        public void setMapsActivity(MiUbicacion mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            //  loc.getLatitude();
            //  loc.getLongitude();
            //  String Text = "Lat = "+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
            //agregarMarcador(loc.getLatitude(),loc.getLongitude());
            UltimaLatitud=loc.getLatitude();
            UltimaLongitud=loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            // mensaje1.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //  mensaje1.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

}