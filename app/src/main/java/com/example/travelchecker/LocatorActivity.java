package com.example.travelchecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.travelchecker.Model.Presentor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class LocatorActivity extends AppCompatActivity implements LocationListener {

    private BottomNavigationView mBottomNavigationView;
    private MenuItem mSettingsItem;
    private MenuItem mNotificationsItem;
    private MenuItem mLocatorItem;
    private TextView txtLat;

    private int inHomePings = 0;
    private int outHomePings = 0;
    private int inWorkPings = 0;
    private int outWorkPings = 0;
    private int inSchoolPings = 0;
    private int outSchoolPings = 0;

    private Presentor presentor = new Presentor();

    protected LocationManager locationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        getSupportActionBar(). hide();

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mSettingsItem = mBottomNavigationView.getMenu().getItem(0);
        mSettingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(LocatorActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        mNotificationsItem = mBottomNavigationView.getMenu().getItem(1);
        mNotificationsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(LocatorActivity.this, NotificationsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        mLocatorItem = mBottomNavigationView.getMenu().getItem(2);
        mLocatorItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });






        txtLat = (TextView) findViewById(R.id.textview1);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());

        if(inHomeRadius(location) && !presentor.isInHome()){
            inHomePings++;
            outHomePings = 0;
            if(inHomePings >= 3){
                presentor.setInHome(true);
                presentor.addMessage(new java.util.Date().toString(), "arrived at home");
            }
        }
        else if(!inHomeRadius(location) && presentor.isInHome()){
            outHomePings++;
            inHomePings = 0;
            if(outHomePings >= 3) {
                presentor.setInHome(false);
                presentor.addMessage(new java.util.Date().toString(), "leaving home now");
            }
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    private boolean inHomeRadius(Location ping){
        if(calculateDistance(ping, presentor.getHomeLat(), presentor.getHomeLong()) < presentor.getHomeRadius()){
            return true;
        }
        return false;
    }

    private boolean inWorkRadius(Location ping){
        if(calculateDistance(ping, presentor.getWorkLat(), presentor.getWorkLong()) < presentor.getWorkRadius()){
            return true;
        }
        return false;
    }

    private boolean inSchoolRadius(Location ping){
        if(calculateDistance(ping, presentor.getSchoolLat(), presentor.getSchoolLong()) < presentor.getSchoolRadius()){
            return true;
        }
        return false;
    }

    private double calculateDistance(Location ping, double centerLat, double centerLong){
        double latDistance = Math.abs(ping.getLatitude() - centerLat);
        double longDistance = Math.abs(ping.getLongitude() - centerLong);
        double totalDistance = Math.sqrt(Math.pow(latDistance, 2) + Math.pow(longDistance, 2));
        return totalDistance * 111321;

    }


}
