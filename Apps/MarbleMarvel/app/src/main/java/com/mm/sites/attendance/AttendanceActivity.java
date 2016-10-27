package com.mm.sites.attendance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.labours.Labour;
import com.mm.storage.local.db.sites.allocations.labour.SiteLabourStorage;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceActivity extends AppCompatActivity implements View.OnClickListener{

    ListView lv_dateList;
    TextView tv_lName;
    Button bt_enter, bt_next;
    AttendanceAdapter attAdapter;
    Integer siteId;
    SiteLabourStorage slStorage;
    ArrayList<Labour> labours;
    Integer lCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        slStorage = new SiteLabourStorage();
        labours = new ArrayList<Labour>();

        Intent intent = getIntent();
        siteId = (Integer) intent.getIntExtra("site_id",0);

        populateLabours(siteId);
        //Toast.makeText(AttendanceActivity.this, "LCount: "+labours.size(), Toast.LENGTH_SHORT).show();


        lv_dateList = (ListView)findViewById(R.id.att_date_list);
        tv_lName = (TextView)findViewById(R.id.att_labour_name);
        bt_enter = (Button) findViewById(R.id.att_enter);
        bt_enter.setOnClickListener(this);
        bt_next = (Button) findViewById(R.id.att_next);
        bt_next.setOnClickListener(this);
        lCount=0;



        attAdapter = new AttendanceAdapter(this);
        Calendar startCal = Calendar.getInstance();
        startCal.set(2016,9,25);

        Calendar endCal = Calendar.getInstance();
        endCal.set(2016,10,10);
        attAdapter.setDateRange(startCal,endCal);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(labours.size() >0) {
            tv_lName.setText("Enter Attendance for "+labours.get(lCount).name +":");
            lv_dateList.setAdapter(attAdapter);
            Toast.makeText(this, "Count:" + attAdapter.getCount(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AttendanceActivity.this, "No Labour Allocated to this Site", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean populateLabours(Integer site_id){
        Cursor cursor;
        //cursor = lStorage.getAllLabours(this);
        cursor =slStorage.getLabours(this,site_id);
         if (cursor != null && cursor.getCount() > 0) {
             cursor.moveToFirst();
             do {
                 Labour labour = new Labour();
                 labour.id = cursor.getInt(0);
                 labour.name =  cursor.getString(1);
                 labour.fathersName = cursor.getString(2);
                 labour.place = cursor.getString(3);
//                 labour.doj = cursor.getString(4);
//                 labour.rate = cursor.getDouble(5);

                 labours.add(labour);
             }while(cursor.moveToNext());

        }else{
             // TODO: Notify ??
         }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.att_enter:
                Toast.makeText(AttendanceActivity.this, "Saving Attendence...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.att_next:

                //if(lCount  < labours.size())
                tv_lName.setText("Enter Attendance for "+labours.get(lCount).name +":");
                lCount++;
                if(lCount == labours.size()){
                    bt_next.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(),com.mm.sites.SiteActivity.class));
                }
                break;
        }
    }
}
