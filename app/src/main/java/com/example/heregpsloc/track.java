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
    private String trackname;
    private List<datapoint> datapoints=new ArrayList<>();
    public void setdatapoint(datapoint _dp){datapoints.add(_dp);}
    public List<datapoint> getDatapoints(){return datapoints;}

    private void sortdatapointsbytime(){
    Collections.sort(datapoints, new Comparator<datapoint>() {
        @Override
        public int compare(datapoint lhs, datapoint rhs) {
            return lhs.datetime.compareTo(rhs.datetime);//ascending
        }
    });
    }


    double distance() {
        //does not contain altitude
        sortdatapointsbytime();

        boolean init=true;
        double totaldistance=0;
        datapoint dp_prev = null;
        for (datapoint dp:datapoints){
            if (init){
                init=false;}
            else {
                Location locationA = new Location("point A");

                locationA.setLatitude(dp_prev.latitude);
                locationA.setLongitude(dp_prev.longitude);

                Location locationB = new Location("point B");

                locationB.setLatitude(dp.latitude);
                locationB.setLongitude(dp.longitude);

                totaldistance = totaldistance+locationA.distanceTo(locationB);
            }
            dp_prev=dp;
        }
        return totaldistance;
    }

    long pos_climb() {


        throw new UnsupportedOperationException();
    }

    long neg_climb() {
        throw new UnsupportedOperationException();
    }

    protected void send2db() {
        throw new UnsupportedOperationException();
    }
}
