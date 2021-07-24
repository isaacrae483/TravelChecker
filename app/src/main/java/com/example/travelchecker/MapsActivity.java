package com.example.travelchecker;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelchecker.Model.Presentor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float DEFAULT_ZOOM = 15.0f;
    private Presentor presentor = new Presentor();
    private TextView title;
    private TextView selectedLat;
    private TextView selectedLong;
    private EditText selectedRadius;
    private Button submitButton;

    private Marker homeMarker;
    private Marker workMarker;
    private Marker schoolMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        title = findViewById(R.id.map_title);
        selectedLat = findViewById(R.id.map_lat);
        selectedLong = findViewById(R.id.map_long);
        selectedRadius = findViewById(R.id.map_radius);
        selectedRadius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String radius = s.toString();
                if(!radius.equals("") && radius != null){
                    try {
                        if(presentor.isSettingHome()){
                            presentor.setHomeRadius(Integer.parseInt(radius));
                        }
                        else if(presentor.isSettingWork()){
                            presentor.setWorkRadius(Integer.parseInt(radius));
                        }
                        else{
                            presentor.setSchoolRadius(Integer.parseInt(radius));
                        }
                    }catch(Exception e){
                        Toast.makeText(getBaseContext(), "Invalid Radius", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(presentor.isSettingHome()){
            title.setText("Select Home Location");
            if(presentor.getHomeLat() != null){
                selectedLat.setText(presentor.getHomeLat().toString());
            }
            if(presentor.getHomeLong() != null){
                selectedLong.setText(presentor.getHomeLong().toString());
            }
            selectedRadius.setText(presentor.getHomeRadius().toString());
        }
        else if(presentor.isSettingWork()){
            title.setText("Select Work Location");
            if(presentor.getWorkLat() != null){
                selectedLat.setText(presentor.getWorkLat().toString());
            }
            if(presentor.getWorkLong() != null){
                selectedLong.setText(presentor.getWorkLong().toString());
            }
            selectedRadius.setText(presentor.getWorkRadius().toString());
        }
        else{
            title.setText("Select School Location");
            if(presentor.getSchoolLat() != null){
                selectedLat.setText(presentor.getSchoolLat().toString());
            }
            if(presentor.getSchoolLong() != null){
                selectedLong.setText(presentor.getSchoolLong().toString());
            }
            selectedRadius.setText(presentor.getSchoolRadius().toString());
        }

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double value = presentor.getHomeLat();
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        LatLng home = new LatLng(presentor.getHomeLat(), presentor.getHomeLong());
        homeMarker = mMap.addMarker(new MarkerOptions().position(home).title("Home"));

        LatLng work = new LatLng(presentor.getWorkLat(), presentor.getWorkLong());
        workMarker = mMap.addMarker(new MarkerOptions().position(work).title("Work"));

        LatLng school = new LatLng(presentor.getSchoolLat(), presentor.getSchoolLong());
        schoolMarker =  mMap.addMarker(new MarkerOptions().position(school).title("School"));


        if(presentor.isSettingHome()){
            selectedLat.setText(presentor.getHomeLat().toString());
            selectedLong.setText(presentor.getHomeLong().toString());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, DEFAULT_ZOOM));
        }
        else if(presentor.isSettingWork()){
            selectedLat.setText(presentor.getWorkLat().toString());
            selectedLong.setText(presentor.getWorkLong().toString());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(work, DEFAULT_ZOOM));
        }
        else{
            selectedLat.setText(presentor.getSchoolLat().toString());
            selectedLong.setText(presentor.getSchoolLong().toString());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(school, DEFAULT_ZOOM));
        }



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                System.out.println(latLng);
                if(presentor.isSettingHome()){
                    presentor.setHomeLat(latLng.latitude);
                    selectedLat.setText(Double.toString(latLng.latitude));
                    presentor.setHomeLong(latLng.longitude);
                    selectedLong.setText(Double.toString(latLng.longitude));
                    homeMarker.remove();
                    homeMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Home"));
                }
                else if(presentor.isSettingWork()){
                    presentor.setWorkLat(latLng.latitude);
                    selectedLat.setText(Double.toString(latLng.latitude));
                    presentor.setWorkLong(latLng.longitude);
                    selectedLong.setText(Double.toString(latLng.longitude));
                    workMarker.remove();
                    workMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Work"));
                }
                else{
                    presentor.setSchoolLat(latLng.latitude);
                    selectedLat.setText(Double.toString(latLng.latitude));
                    presentor.setSchoolLong(latLng.longitude);
                    selectedLong.setText(Double.toString(latLng.longitude));
                    schoolMarker.remove();
                    schoolMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("School"));
                }
            }
        });
    }
}
