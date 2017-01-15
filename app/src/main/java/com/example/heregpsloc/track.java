package com.example.heregpsloc;

import android.location.Location;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by janniklas on 14.01.17.
 */

public class track {
    //constructor
    public track(String _trackname){trackname=_trackname;}
    //properties
    private String trackname;
    private List<datapoint> datapoints=new ArrayList<>();
    private double distance;
    private double climb;
    private double positive_climb;
    private double negative_climb;
    //setter
    public void setdatapoint(datapoint _dp){datapoints.add(_dp);}
    //getter
    public List<datapoint> getDatapoints(){return datapoints;}
    public double distance(){return distance;}
    public double climb(){return climb;}
    public double positive_climb(){return positive_climb;}
    public double negative_climb(){return negative_climb;}

    //calc functions
    private void sortdatapointsbytime(){
    Collections.sort(datapoints, new Comparator<datapoint>() {
        @Override
        public int compare(datapoint lhs, datapoint rhs) {
            return lhs.datetime.compareTo(rhs.datetime);//ascending
        }
    });
    }
    private void analyse_date(){
        initvars();
        sortdatapointsbytime();
        boolean init=true;
        double totaldistance=0;
        datapoint dp_prev = null;
        for (datapoint dp:datapoints){
            if (init) init=false;
            else {
                distance=distance+calc_distance(dp_prev,dp);
                double current_climb=calc_climb(dp_prev,dp);
                if (current_climb>0) positive_climb=positive_climb+current_climb;
                else negative_climb=negative_climb+current_climb;
            }
            dp_prev=dp;
        }
        climb=positive_climb+negative_climb;
    }
    private void initvars(){
         distance=0;
         climb=0;
         positive_climb=0;
         negative_climb=0;
    }

    private double calc_distance(datapoint dp1, datapoint dp2) {
        Location locationA = new Location("point A");
        locationA.setLatitude(dp1.latitude);
        locationA.setLongitude(dp1.longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(dp2.latitude);
        locationB.setLongitude(dp2.longitude);
        return locationA.distanceTo(locationB);
    }

    private double calc_climb(datapoint dp1, datapoint dp2){
        return dp2.altitude-dp1.altitude;
    }

    protected void send2db() {
        throw new UnsupportedOperationException();
    }
}
