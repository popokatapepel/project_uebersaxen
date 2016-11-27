package com.example.heregpsloc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

abstract class RECORDER extends AppCompatActivity {
    //as long as the service is running service_running=true data are written to db
    protected boolean service_running = true;
    protected long millisec_wait = 1000;
    protected db appdatabse;


    protected void run_service() {
        while (service_running) {
            Location loc = getGPS();
            write2db(loc);
            try {
                Thread.sleep(millisec_wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void write2db(Location loc);

    protected abstract Location getGPS();

    protected abstract void credb();
}

public class recorder extends RECORDER {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        appdatabse = new db();

        run_service();
    }


    //eventhandler onclick start record
    public void startrecord(View view) {
        service_running = true;
        run_service();
    }

    //eventhandler onclick stop record
    public void stoprecord(View view) {
        service_running = false;
    }


    @Override
    protected void write2db(Location loc) {
        appdatabse.writeloc(loc);
    }

    @Override
    protected Location getGPS() {
        LocationManager locationManager;
        Location location;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        location= locationManager.getLastKnownLocation("gps");
        return location;
    }

    @Override
    protected void credb() {
        throw new UnsupportedOperationException();
    }
}
