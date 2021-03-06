package com.example.heregpsloc;

import java.util.Calendar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class tacho extends MenuAppCompatActivity implements OnClickListener {

    private LocationManager locationManager;
    private Button checkBox;
    private RadioButton radio0;
    private RadioButton radio1;
    private Button checkBoxRefresh;
    private TextView textView;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ask for permission, if permission is denied kill process
        //create new app process
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        while (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            // as long as permission is not granted wait for user to accept
            //check best practice here
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainthread();
    }

    void mainthread()
    {
        //when gps-permission is granted this thread will start the app

        setContentView(R.layout.activity_tacho);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkBox = (Button) findViewById(R.id.checkBox1);
        radio0 = (RadioButton) findViewById(R.id.radio0);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        checkBoxRefresh = (Button) findViewById(R.id.checkBox2);
        textView = (TextView) findViewById(R.id.textView1);

        checkBox.setOnClickListener(this);
        radio0.setOnClickListener(this);
        radio1.setOnClickListener(this);
        checkBoxRefresh.setOnClickListener(this);

        updateThread.start();
    }

    Thread updateThread = new Thread() {
        public void run() {
            try {
                while (true) {
                    tacho.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateText();
                        }
                    });
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignore) {
                // Thread was interrupted ==> stop loop
            }
        }
    };

    private void setButtonState() {
        boolean isGps = locationManager.isProviderEnabled("gps");
        //checkBox.setChecked(isGps);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setButtonState();
    }

    @Override
    public void onClick(View v) {
        if (v == checkBox)
            clickCheckBox();
        else if (v == checkBoxRefresh)
            clickCheckBoxRefresh();
        else
            setButtonState(); // for radio buttons
    }

    private void clickCheckBoxRefresh() {
            String provider;
            if (radio0.isChecked())
                provider = LocationManager.GPS_PROVIDER;
            else
                provider = LocationManager.NETWORK_PROVIDER;
            locationManager.requestLocationUpdates(provider, 1000, 0,
                    locationListener);

    }

    private void clickCheckBox() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private void updateText() {

        Location location;
        if (radio0.isChecked())
            location = locationManager.getLastKnownLocation("gps");
        else if (radio1.isChecked())
            location = locationManager.getLastKnownLocation("network");
        else
            location = null;
        if (location == null) {
            if (radio0.isChecked()) {
                if (locationManager.isProviderEnabled("gps"))
                    textView.setText(R.string.no_gps);
                else
                    textView.setText(R.string.turn_on);
            } else
                textView.setText(R.string.network_disabled);
        } else {
            textView.setText(Html.fromHtml(getString(R.string.position_text, location.getAltitude(),location.getSpeed())));

        }
    }
}