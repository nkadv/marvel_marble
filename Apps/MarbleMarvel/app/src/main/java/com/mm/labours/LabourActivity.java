package com.mm.labours;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mm.entry.R;

public class LabourActivity extends AppCompatActivity {

    TextView id,name,fname,location,rate,doj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour);

        Intent intent = getIntent();
        Labour labour = (Labour) intent.getSerializableExtra("labour");

        id = (TextView)findViewById(R.id.labour_id);
        id.setText("ID: "+ labour.id);

        name = (TextView)findViewById(R.id.labour_name);
        name.setText("Name: "+ labour.name);

        fname = (TextView)findViewById(R.id.labour_f_name);
        fname.setText("s/0: "+ labour.fathersName);

        location = (TextView)findViewById(R.id.labour_location);
        location.setText("Native Place: "+ labour.place);

        rate = (TextView)findViewById(R.id.labour_rate);
        //rate.setText("Rate: "+ labour.rate.toString());

        doj = (TextView)findViewById(R.id.labour_doj);
        //doj.setText("Working Since: "+ labour.doj);


    }
}
