package com.example.travelchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelchecker.Model.Presentor;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SettingsActivity extends AppCompatActivity {

    private TextView mUsername;

    private BottomNavigationView mBottomNavigationView;
    private MenuItem mSettingsItem;
    private MenuItem mNotificationsItem;
    private MenuItem mLocatorItem;

    private Presentor presentor = new Presentor();

    private TextView homeLatTextView;
    private TextView homeLongTextView;
    private Button setHomeButton;
    private EditText homeRadiusEditText;

    private TextView workLatTextView;
    private TextView workLongTextView;
    private Button setWorkButton;
    private EditText workRadiusEditText;

    private TextView schoolLatTextView;
    private TextView schoolLongTextView;
    private Button setSchoolButton;
    private EditText schoolRadiusEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar(). hide();

        mUsername = (TextView) findViewById(R.id.username);
        mUsername.setText(presentor.getUsername());


        //home stuff
        homeLatTextView = findViewById(R.id.home_lat);
        if(presentor.getHomeLat() != null){
            homeLatTextView.setText(presentor.getHomeLat().toString());
        }
        homeLongTextView = findViewById(R.id.home_long);
        if(presentor.getHomeLong() != null){
            homeLongTextView.setText(presentor.getHomeLong().toString());
        }

        setHomeButton = (Button) findViewById(R.id.select_home_location);
        setHomeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //launch the map to select a work loaction
                presentor.setSettingHome(true);
                presentor.setSettingWork(false);
                presentor.setSettingSchool(false);
                Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        homeRadiusEditText = (EditText) findViewById(R.id.home_radius);
        if(presentor.getHomeRadius() != null){
            homeRadiusEditText.setText(presentor.getHomeRadius().toString());
        }
        homeRadiusEditText.addTextChangedListener(new TextWatcher() {
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
                        presentor.setHomeRadius(Integer.parseInt(radius));
                    }catch(Exception e){
                        Toast.makeText(getBaseContext(), "Invalid Radius", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //work stuff
        workLatTextView = findViewById(R.id.work_lat);
        if(presentor.getWorkLat() != null){
            workLatTextView.setText(presentor.getWorkLat().toString());
        }
        workLongTextView = findViewById(R.id.work_long);
        if(presentor.getWorkLong() != null){
            workLongTextView.setText(presentor.getWorkLong().toString());
        }

        setWorkButton = (Button) findViewById(R.id.select_work_location);
        setWorkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //launch the map to select a work loaction
                presentor.setSettingHome(false);
                presentor.setSettingWork(true);
                presentor.setSettingSchool(false);
                Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        workRadiusEditText = (EditText) findViewById(R.id.work_radius);
        if(presentor.getWorkRadius() != null){
            workRadiusEditText.setText(presentor.getWorkRadius().toString());
        }
        workRadiusEditText.addTextChangedListener(new TextWatcher() {
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
                        presentor.setWorkRadius(Integer.parseInt(radius));
                    }catch(Exception e){
                        Toast.makeText(getBaseContext(), "Invalid Radius", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //school stuff
        schoolLatTextView = findViewById(R.id.school_lat);
        if(presentor.getSchoolLat() != null){
            schoolLatTextView.setText(presentor.getSchoolLat().toString());
        }
        schoolLongTextView = findViewById(R.id.school_long);
        if(presentor.getSchoolLong() != null){
            schoolLongTextView.setText(presentor.getSchoolLong().toString());
        }

        setSchoolButton = (Button) findViewById(R.id.select_school_location);
        setSchoolButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //launch the map to select a work loaction
                presentor.setSettingHome(false);
                presentor.setSettingWork(false);
                presentor.setSettingSchool(true);
                Intent intent = new Intent(SettingsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        schoolRadiusEditText = (EditText) findViewById(R.id.school_radius);
        if(presentor.getSchoolRadius() != null){
            schoolRadiusEditText.setText(presentor.getSchoolRadius().toString());
        }
        schoolRadiusEditText.addTextChangedListener(new TextWatcher() {
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
                        presentor.setSchoolRadius(Integer.parseInt(radius));
                    }catch(Exception e){
                        Toast.makeText(getBaseContext(), "Invalid Radius", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
        mSettingsItem = mBottomNavigationView.getMenu().getItem(0);
        mSettingsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        mNotificationsItem = mBottomNavigationView.getMenu().getItem(1);
        mNotificationsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(SettingsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                return true;
            }
        });

        mLocatorItem = mBottomNavigationView.getMenu().getItem(2);
        mLocatorItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(SettingsActivity.this, LocatorActivity.class);
                startActivity(intent);
                return true;
            }
        });

    }

}
