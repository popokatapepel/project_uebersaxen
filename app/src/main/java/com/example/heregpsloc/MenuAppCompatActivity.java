package com.example.heregpsloc;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by janniklas on 04.12.16.
 * This class will be used as new base class for activities
 * Here all eventhandler for the menu bar can be listed
 */

public class MenuAppCompatActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.tacho:
                break;
            case R.id.recorder:
                break;
            default:
                throw new UnsupportedOperationException("There is no action defined for the Menu Item (ID: "+ id+")");
        }
        if (id == R.id.tacho) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

