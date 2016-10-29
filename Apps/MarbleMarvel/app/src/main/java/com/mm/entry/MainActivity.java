package com.mm.entry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mm.labours.ViewLaboursActivity;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.sites.SiteStorage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    ListView lvActiveSites;
    SiteStorage sStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Content Pane*/
        lvActiveSites = (ListView)findViewById(R.id.m_active_s_list);
        lvActiveSites.setOnItemClickListener(this);
        lvActiveSites.setDividerHeight(10);
        sStorage = new SiteStorage();
        populateActiveSites("");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_labours:
                Toast.makeText(this,"View Labours",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ViewLaboursActivity.class));
                break;

            case R.id.nav_add_labour:
                Toast.makeText(this,"Add Labours",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,com.mm.labours.AddLabourActivity.class));
                break;

            case R.id.nav_materials:
                startActivity(new Intent(this, com.mm.materials.ViewMaterialsActivity.class));
                break;

            case R.id.nav_add_material:
                startActivity(new Intent(this, com.mm.materials.AddMaterialActivity.class));
                break;

            case R.id.nav_add_site:
                startActivity(new Intent(this, com.mm.sites.AddSiteActivity.class));
                break;

            case R.id.nav_active_sties:
                startActivity(new Intent(this, com.mm.entry.MainActivity.class));
                break;

            case R.id.nav_add_employees:
                startActivity(new Intent(this,com.mm.employees.AddEmployeeActivity.class));
                break;

            case R.id.nav_employees:
                startActivity(new Intent(this,com.mm.employees.ViewEmployeesActivity.class));
                break;

            default:
                Toast.makeText(this,"Not Supported",Toast.LENGTH_SHORT).show();
                break;


        }
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void populateActiveSites(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = sStorage.getActiveSites(this);

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Sites.NAME, DBSchema.Sites.LOCATION};
                int[] to = new int[]{R.id.as_site_name,R.id.as_site_location};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, R.layout.active_site_list_layout, cursor, from, to, 0);
                lvActiveSites.setAdapter(sCA);
            }
        }
        else{
            cursor = sStorage.getSites(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Sites.NAME, DBSchema.Sites.LOCATION};
                int[] to = new int[]{R.id.as_site_name,R.id.as_site_location};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, R.layout.active_site_list_layout, cursor, from, to, 0);
//                int[] to = new int[]{android.R.id.text1,android.R.id.text2};
//                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, R.layout.active_site_list_layout, cursor, from, to, 0);
                lvActiveSites.setAdapter(sCA);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


        if(adapterView.getId() == R.id.m_active_s_list)
        {
            /*Launch Site*/
            Cursor c = ((SimpleCursorAdapter)adapterView.getAdapter()).getCursor();
            c.moveToPosition(position);
//            Site site = new Site();
//            site.id = c.getInt(0);
//            site.name =  c.getString(1);
//            site.location = c.getString(2);
//            site.startDate = c.getString(3);

            Integer siteId = c.getInt(0);

            Intent intent = new Intent(this, com.mm.sites.SiteActivity.class);
            intent.putExtra("siteId",siteId);
            startActivity(intent);

            //Toast.makeText(this,"Launching Site Id: "+ site.id, Toast.LENGTH_SHORT).show();
        }

    }
}

