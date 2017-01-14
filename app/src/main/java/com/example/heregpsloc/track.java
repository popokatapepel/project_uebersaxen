package com.example.heregpsloc;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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


    long distance() {
        throw new UnsupportedOperationException();
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
