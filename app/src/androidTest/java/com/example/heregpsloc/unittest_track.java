package com.example.heregpsloc;

import org.junit.Test;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;



/**
 * Created by janniklas on 15.01.17.
 */

public class unittest_track {
    track trk;
    double result_climb, result_pos_climb, result_neg_climb, result_distance;


    private void init(){
        trk=new track("testrack");
        datapoint dp;
        long time=System.currentTimeMillis();
        trk.setdatapoint(new datapoint(47.235909,9.591282,100,10.3,time));
        time=time+1500;
        trk.setdatapoint(new datapoint(49.049788, 8.462741,200,10.3,time));
        //estimated distance to point 1: 218.1km (135.52mi)
        time=time+9000;
        trk.setdatapoint(new datapoint(48.779006, 11.405524,30,10.3,time));
        //estimated distance to point 1: 217.13km (134.92mi)
        //total estimated distance:

        result_climb=-70;
        result_pos_climb=100;
        result_neg_climb=-170;
        result_distance=218100+217130;
        trk.analyse_track();
    }


    @Test
    public void test_climb(){
        init();
        assertEquals(trk.climb(),result_climb,0);
    }
    @Test
    public void test_negclimb(){
        init();
        assertEquals(trk.negative_climb(),result_neg_climb,0);
    }
    @Test
    public void test_posclimb(){
        init();
        assertEquals(trk.positive_climb(),result_pos_climb,0);
    }
    @Test
    public void test_distance(){
        init();
        assertEquals(trk.distance(),result_distance,result_distance*0.05);
        //since values are only estimated an accuracy of 5% is sufficient
    }

}
