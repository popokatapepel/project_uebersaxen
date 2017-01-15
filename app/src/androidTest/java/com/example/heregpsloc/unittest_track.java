package com.example.heregpsloc;

import org.junit.Test;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;



/**
 * Created by janniklas on 15.01.17.
 */

public class unittest_track {
    track trk;
    double result_climb, result_pos_climb, result_neg_climb;


    private void init(){
        trk=new track("testrack");
        datapoint dp;
        long time=System.currentTimeMillis();
        trk.setdatapoint(new datapoint(0,0,100,10.3,time));
        time=time+1500;
        trk.setdatapoint(new datapoint(0,0,200,10.3,time));
        time=time+9000;
        trk.setdatapoint(new datapoint(0,0,30,10.3,time));

        result_climb=-70;
        result_pos_climb=100;
        result_neg_climb=-170;
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

}
