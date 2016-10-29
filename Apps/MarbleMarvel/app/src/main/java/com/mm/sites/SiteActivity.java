package com.mm.sites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.labours.Labour;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.labour.LabourStorage;
import com.mm.storage.local.db.materials.MaterialStorage;
import com.mm.storage.local.db.sites.SiteStorage;
import com.mm.storage.local.db.sites.allocations.labour.SiteLabourStorage;
import com.mm.storage.local.db.sites.allocations.material.SiteMaterialStorage;

public class SiteActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    int siteId;
    TextView name,location,date;
    TextView siteLabourDesc;
    TextView siteMaterialDesc;

    LabourStorage lStorage;
    SiteLabourStorage slStorage;
    MaterialStorage mStorage;
    SiteMaterialStorage smStorage;
    SiteStorage sStorage;
    SimpleCursorTreeAdapter sCTA;

    ImageView labour_ec;
    boolean labourCollapse;
    ListView labour_list;

    ImageView material_ec;
    boolean materialCollapse;
    ListView material_list;
    CardView labour_card_view;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        sharedPreferences = getSharedPreferences("site_details", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        siteId = (Integer) intent.getIntExtra("siteId",0);
        if(siteId == 0)
        {
            // Get From Preference if it doesn't exit in intent
            siteId = sharedPreferences.getInt("SITE_ID",0);
        }else{
            //Save in Preference file
            sharedPreferences.edit().putInt("SITE_ID",siteId).apply();
        }
        //Site site = (Site) intent.getSerializableExtra("site");
        //Toast.makeText(this,"SiteId: "+siteId, Toast.LENGTH_SHORT).show();
        sStorage = new SiteStorage();
        Site site = getSiteDetails(siteId);


        if(site == null)
            Toast.makeText(this,"Site is empty", Toast.LENGTH_SHORT).show();

        name = (TextView)findViewById(R.id.site_name);
        location = (TextView)findViewById(R.id.site_location);
        date = (TextView)findViewById(R.id.site_startDate);

        //Toast.makeText(this,"Site Id: "+ site,Toast.LENGTH_SHORT).show();

        if(site != null) {
            siteId = site.id;
            name.setText("Name: " + site.name);
            location.setText("Location: " + site.location);
            date.setText("StartDate: " + site.startDate);
            //Toast.makeText(this,"SiteName: "+site.name, Toast.LENGTH_LONG).show();
        }
        lStorage = new LabourStorage();
        mStorage = new MaterialStorage();
        smStorage = new SiteMaterialStorage();
        slStorage = new SiteLabourStorage();

        labour_ec=(ImageView) findViewById(R.id.site_labour_ec);
        labour_ec.setOnClickListener(this);
        labourCollapse = true;

        labour_list = (ListView)findViewById(R.id.site_labour_list);
        labour_list.setOnItemClickListener(this);


        material_ec=(ImageView) findViewById(R.id.site_material_ec);
        material_ec.setOnClickListener(this);
        materialCollapse = true;

        material_list = (ListView)findViewById(R.id.site_material_list);

        siteMaterialDesc = (TextView) findViewById(R.id.site_material_desc);
        siteLabourDesc = (TextView)findViewById(R.id.site_labours_desc);
        siteLabourDesc.setOnClickListener(this);
        siteMaterialDesc.setOnClickListener(this);


        labour_card_view = (CardView)findViewById(R.id.site_labour_list_card);


    }

    private Site getSiteDetails(Integer siteId){

        Site site = sStorage.getSite(this,siteId);
        return site;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_site_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.site_menu_add_labour:
                intent = new Intent(this, com.mm.sites.expenses.labour.AllocateLabour.class);
                intent.putExtra("site_id",siteId);
                startActivity(intent);
                break;

            case R.id.site_menu_add_material_expense:
                intent = new Intent(this, com.mm.sites.expenses.materials.AllocateMaterial.class);
                intent.putExtra("site_id",siteId);
                startActivity(intent);
                break;
            case R.id.site_menu_attendance:
                intent = new Intent(this, com.mm.sites.attendance.AttendanceActivity.class);
                intent.putExtra("site_id",siteId);
                startActivity(intent);
                break;
            case R.id.site_menu_transfer_labour:

                Toast.makeText(SiteActivity.this, "siteId: "+siteId, Toast.LENGTH_SHORT).show();
                intent = new Intent(this, com.mm.sites.expenses.labour.TransferLabour.class);
                intent.putExtra("site_id",siteId);

                startActivity(intent);
                break;


            default:
                break;
        }

        return true;
    }

    private boolean populateLabours(){
        Cursor cursor;
        //cursor = lStorage.getAllLabours(this);
       cursor =slStorage.getLabours(this,siteId);
        if (cursor != null) {
            String[] from = new String[]{DBSchema.Labours.NAME, DBSchema.Labours.LABOUR_ID,
                    DBSchema.Labours.FATHER_NAME, DBSchema.Labours.PLACE};
            int[] to = new int[]{R.id.ll_name, R.id.ll_id, R.id.ll_fname, R.id.ll_place};
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.labour_list_layout, cursor, from, to, 0);
            labour_list.setAdapter(simpleCursorAdapter);
        }
        return true;
    }

    private boolean populateMaterials() {
        Cursor cursor;
        //cursor = mStorage.getAllMaterials(this);
        cursor = smStorage.getMaterialExpenses(this,siteId);

        if (cursor != null) {
            String[] from = new String[]{DBSchema.Materials.NAME};
            int[] to = new int[]{android.R.id.text1};
            SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, from, to, 0);
            material_list.setAdapter(sCA);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.site_labour_ec:
            case R.id.site_labours_desc:
                if(labourCollapse){
                    labour_ec.setBackgroundResource(R.drawable.expand);
                    labourCollapse = false;
                    labour_list.setVisibility(View.VISIBLE);
                    labour_card_view.setVisibility(View.VISIBLE);

                    populateLabours();
                    //show labour list
                }else{
                    labourCollapse = true;
                    labour_ec.setBackgroundResource(R.drawable.collapse);
                    labour_list.setVisibility(View.GONE);
                    labour_card_view.setVisibility(View.GONE);
                    //hide labour list view
                }
                break;
            case R.id.site_material_ec:
            case R.id.site_material_desc:
                if(materialCollapse){
                    material_ec.setBackgroundResource(R.drawable.expand);
                    materialCollapse = false;
                    material_list.setVisibility(View.VISIBLE);
                    populateMaterials();

                }else{
                    materialCollapse = true;
                    material_ec.setBackgroundResource(R.drawable.collapse);
                    material_list.setVisibility(View.GONE);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(adapterView.getId()){
            case R.id.site_labour_list:
                 /*Launch Labour*/
                Cursor c = ((SimpleCursorAdapter)adapterView.getAdapter()).getCursor();
                c.moveToPosition(position);
                Labour labour = new Labour();
                labour.id = c.getInt(0);
                labour.name =  c.getString(1);
                labour.fathersName = c.getString(2);
                labour.place = c.getString(3);
//                labour.doj = c.getString(4);
//                labour.rate = c.getDouble(5);

                Intent intent = new Intent(this, com.mm.labours.LabourActivity.class);
                intent.putExtra("labour",labour);
                startActivity(intent);

                Toast.makeText(this,"Launching Labour Id: "+ labour.id, Toast.LENGTH_SHORT).show();

                break;

            default:
                break;
        }
    }
}

//  TODO: Site - > Main Activity on parent back button