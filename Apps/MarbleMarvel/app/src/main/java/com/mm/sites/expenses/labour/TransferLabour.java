package com.mm.sites.expenses.labour;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.labours.Labour;
import com.mm.sites.Site;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.sites.SiteStorage;
import com.mm.storage.local.db.sites.allocations.labour.SiteLabourStorage;

import java.util.ArrayList;
import java.util.Date;

public class TransferLabour extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    SiteLabourStorage slStorage;
    SiteStorage sStorage;
    Integer siteId;
    Integer toSiteId;
    ListView lvLabourList;
    Spinner spToSite;
    Button bTransfer;
    ArrayList<String> spSiteList;
    ArrayList<Site> SiteList;

    ArrayList<Labour> siteLabourList;
    TransferLabourAdapter tlAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_labour);
        slStorage = new SiteLabourStorage();
        sStorage = new SiteStorage();
        spSiteList = new ArrayList<String>();
        SiteList = new ArrayList<Site>();

        Intent intent = getIntent();
        siteId = (Integer) intent.getIntExtra("site_id",0);

        Toast.makeText(TransferLabour.this, "siteId:"+siteId, Toast.LENGTH_SHORT).show();

        lvLabourList = (ListView)findViewById(R.id.tl_labour_list);
        lvLabourList.setDividerHeight(10);
        populateLabours();

        bTransfer = (Button)findViewById(R.id.tl_transfer);
        bTransfer.setOnClickListener(this);

        spToSite = (Spinner)findViewById(R.id.tl_to_site);
        populateActiveSites();
        spToSite.setOnItemSelectedListener(this);

    }


    private boolean populateLabours(){

        siteLabourList = slStorage.getLabourList(this,siteId);
        tlAdapter= new TransferLabourAdapter(this,siteLabourList);
        lvLabourList.setAdapter(tlAdapter);

        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tl_transfer:
                if(toSiteId == 0) {
                    Toast.makeText(TransferLabour.this, "Select Site", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TransferLabour.this, "Labour C "+lvLabourList.getCount(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void populateActiveSites() {

        Cursor cursor;
        String siteName;

        spSiteList.add("Select Site...");
        Site site1 = new Site();
        site1.id = 0;
        SiteList.add(site1);

        cursor = sStorage.getActiveSites(this);
        cursor.moveToFirst();
        if(cursor.getCount() >0){
            do{
                Site site = new Site();
                site.id = Integer.parseInt(cursor.getString(0));
                site.name = cursor.getString(1);//Name of Site TODO: get rid of explicit numbering ?
                SiteList.add(site);
                spSiteList.add( site.name);
            }while(cursor.moveToNext());
        }
        spSiteList.add("Labour Pool");
        Site site2 = new Site();
        site2.id = 1000;
        SiteList.add(site2);

        //TODO: Add place ??to drop down list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spSiteList);
        spToSite.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() == R.id.tl_to_site){
            toSiteId = SiteList.get(i).id;
            //Toast.makeText(TransferLabour.this, "Selected Site: "+site.id , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


//TODO:
//1. Keep track of selected labour
//2. On Button Click update selected labour in DB


//TODO:
//1. Populate Spinner with ActiveSites(Names)
//2. On Trasnfer Button Click
//    - Get Site number and selected Labour'ids
//    - Update DB
//        - Update labour status in current site
//        - Insert new records against new site