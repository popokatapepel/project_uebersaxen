package com.example.heregpsloc;

import android.location.Location;

/**
 * Created by janniklas on 27.11.16.
 */

interface Idb{
    void writeloc(Location loc);
}

abstract class DB{
    //function checks if app_db already exists
    protected abstract boolean check_if_exist();
    //function creates db and tables
    protected abstract void createdb();
}

//class to handle all interactions with database
public class db extends DB implements Idb {
    //db constructor
    public db(){
        if (!check_if_exist()){
            createdb();
        }
    }
    @Override
    protected boolean check_if_exist() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void createdb() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeloc(Location loc) {
        throw new UnsupportedOperationException();
    }
}
