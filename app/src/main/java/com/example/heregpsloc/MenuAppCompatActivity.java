package com.example.heregpsloc;

import android.content.Intent;
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
        Intent intent=null;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.tacho:
                intent = new Intent(this, tacho.class);
                break;
            case R.id.recorder:
                intent = new Intent(this, recorder.class);
                break;
            default:
                throw new UnsupportedOperationException("There is no action defined for the Menu Item (ID: "+ id+")");
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}

