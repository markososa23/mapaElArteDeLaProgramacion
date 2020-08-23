package com.marcosoft.mapaelartedelaprogramacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSitiosT,btnTiposM,btnMiUbi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSitiosT = (Button) findViewById(R.id.btnSitiosTuristicos);
        btnTiposM =(Button) findViewById(R.id.btnTiposDeMapas);
        btnMiUbi = (Button) findViewById(R.id.btnMiUbicacion);

        btnSitiosT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity1.class);
                startActivity(intent);
            }
        });
        btnTiposM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsTypesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void miUbicacion(View v) {
        Intent intent = new Intent(getApplicationContext(),MiUbicacion.class);
        startActivity(intent);
    }
}