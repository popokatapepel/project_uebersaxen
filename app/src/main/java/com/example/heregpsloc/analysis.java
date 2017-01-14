package com.example.heregpsloc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class analysis extends MenuAppCompatActivity {
    private TextView txtoutput;
    private db_mtb appdatabse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        txtoutput = (TextView) findViewById(R.id.txt_output);
        appdatabse = new db_mtb(this);
        refresh();
    }

    public void event_refresh(View view) {
        refresh();
    }

    private void refresh() {
        txtoutput.setText("");
        txtoutput.setText(appdatabse.getdistincttrackids());
    }


}
