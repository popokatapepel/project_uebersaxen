package com.example.heregpsloc;

import java.util.Date;

/**
 * Created by janniklas on 14.01.17.
 */

public class datapoint {
    //properties
    double longitude;
    double latitude;
    double altitude;
    double velocity;
    Date datetime;

    //constructor setter
    public datapoint(double _longitude, double _latitude, double _altitude, double _velocity, long _datetime){
        longitude=_longitude;
        latitude=_latitude;
        altitude=_altitude;
        velocity=_velocity;
        datetime=new Date(_datetime);
    }

    //getter
    public double getlongitude(){ return longitude;}
    public double getlatitude(){ return latitude; }
    public double getaltitude(){ return altitude; }
    public double getvelocity() {return velocity;}
    public Date getdatetime (){return datetime;}
}
