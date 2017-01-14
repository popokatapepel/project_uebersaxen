package com.example.heregpsloc;

import android.database.Cursor;

/**
 * Created by janniklas on 14.01.17.
 * in this class all raw data analysis is happening
 */
abstract class DATA_ANALYSIS{
    // returns the total distance of a track
    abstract long distance(Cursor trk_cursor);
    // returns the positive and negative climb of a track
    abstract long pos_climb(Cursor trk_cursor);
    abstract long neg_climb(Cursor trk_cursor);

    // to minimize calculation this method is used to send track analysis back to the db once it is finished
    protected abstract void send2db();
}
public class data_analysis extends DATA_ANALYSIS {

    @Override
    long distance(Cursor trk_cursor) {
        throw new UnsupportedOperationException();
    }

    @Override
    long pos_climb(Cursor trk_cursor) {
        throw new UnsupportedOperationException();
    }

    @Override
    long neg_climb(Cursor trk_cursor) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void send2db() {
        throw new UnsupportedOperationException();
    }
}
