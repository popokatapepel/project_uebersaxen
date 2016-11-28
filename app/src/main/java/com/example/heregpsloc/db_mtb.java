package com.example.heregpsloc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

/**
 * Created by janniklas on 27.11.16.
 */

//class to handle all interactions with database
//idea: http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/
public class db_mtb extends SQLiteOpenHelper  {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DB_MTB";
    // Track Table Columns names
    private static final String NAME_TBL_TRACK="tbl_track";
    private static final String CREATE_TBL_TRACK = "CREATE TABLE `"+NAME_TBL_TRACK+"` (\n" +
            "\t`ID`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t`dou_long`\tREAL NOT NULL,\n" +
            "\t`dou_lat`\tREAL NOT NULL,\n" +
            "\t`dou_alt`\tREAL NOT NULL,\n" +
            "\t`dou_vel`\tREAL NOT NULL,\n" +
            "\t`str_track`\tTEXT NOT NULL\n" +
            ");";


    public db_mtb(Context context) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Adding new track_location
    public void addlocation(Location loc, String track_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dou_long", loc.getLongitude());
        values.put("dou_lat", loc.getLatitude());
        values.put("dou_alt", loc.getAltitude());
        values.put("dou_vel", loc.getSpeed());
        values.put("str_track", track_id);

        db.insert(NAME_TBL_TRACK, null, values);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_TRACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + NAME_TBL_TRACK);
        // Creating tables again
        onCreate(db);
    }
}