package com.egco428.maprandom;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double BKlatitude = 13.736717;
    private double BKlongitude = 100.523186;

    EditText lati;
    EditText longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lati = (EditText)findViewById(R.id.EditLati);
        longi = (EditText)findViewById(R.id.EditLongi);

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(BKlatitude, BKlongitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void pressRandom(View view){

        String La = lati.getText().toString();
        String Long = longi.getText().toString();

        double Lati = BKlatitude;
        double Longi = BKlongitude;



        if(La.equals("")||Long.equals("")){
        }
        else{
            Lati = Double.parseDouble(La);
            Longi = Double.parseDouble(Long);
        }


        Random r = new Random();
        double randomLati = -10 + (10 - (-10)) * r.nextDouble();
        double rla =  randomLati + Lati;
        rla =Double.parseDouble(new DecimalFormat("##.####").format(rla));
        lati.setText(String.valueOf(rla));




        Random g = new Random();
        double randomLongi = -10 + ( 10- (-10)) * g.nextDouble();
        double rlo =  randomLongi + Longi;
        rlo =Double.parseDouble(new DecimalFormat("##.####").format(rlo));
        longi.setText(String.valueOf(rlo));


        mMap.addMarker(new MarkerOptions().position(new LatLng(rla, rlo)));

        double dest =  Math.sqrt(Math.pow(Lati - rla,2)+  Math.pow(Longi - rlo,2));


        if(dest < 5){
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(Lati, Longi), new LatLng(rla,rlo))
                    .width(3)
                    .color(Color.GREEN));
        }
        else{
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(Lati, Longi), new LatLng(rla,rlo))
                    .width(3)
                    .color(Color.RED));
        }



    }

}
