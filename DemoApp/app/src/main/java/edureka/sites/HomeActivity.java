package edureka.sites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {


    String moduleNames[]={
            "Active Sites",
            "Closed Sites",
            "Add Site",
            "Labour Records",
            "Add Labours",
            "Material Records",
            "Add Material",
            "Sub Contractor Records",
            "Add Sub Contractor",
            "Site Reports",
            "Labour Reports"
    };

    String labours[]={
            "Ramesh",
            "Suresh",
            "Sravan Singh",
            "Rajesh Gupta",
            "Vikram Bheev",
            "Rajesh Gupta",
            "Parineesh Gaur",
            "Prakash Iyer",
            "Sahib Kaur",
            "Sravan Singh",
            "Rajesh Gupta",
            "Vikram Bheev",
            "Rajesh Gupta",
    };




    TextView moduleName;
    ListView moduleDemos;
    ArrayAdapter<String> sAdapter;
    String packages[];
    String actvities[];
    Sites sites;

    String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sites = Sites.getInstance();

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        moduleName = (TextView)findViewById(R.id.moduleName);
        moduleDemos = (ListView)findViewById(R.id.moduleDemos);
        moduleDemos.setOnItemClickListener(this);
        sAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);


    }

    @Override
    protected void onResume() {
        super.onResume();

         /*Set Active Sites*/
        moduleName.setText(moduleNames[0]);
        sAdapter.clear();
        //sites.getSites(this);
        if((sites.getActiveSites()).length!=0) {
            sAdapter.addAll(sites.getActiveSites());
            moduleDemos.setAdapter(sAdapter);
            activityName = "site";
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        //sites.saveSites();

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id){

            case R.id.add_stie:
                //Toast.makeText(this, "Adding Site...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (this,AddSiteActivity.class);
                intent.putExtra("siteObj", sites);
                startActivity(intent);
                break;

            case R.id.a_sties:
                moduleName.setText(moduleNames[0]);
                sAdapter.clear();
                if((sites.getActiveSites()).length!=0) {
                    sAdapter.addAll(sites.getActiveSites());
                    moduleDemos.setAdapter(sAdapter);
                }
                break;

            case R.id.c_sties:
                moduleName.setText(moduleNames[1]);
                sAdapter.clear();
                sAdapter.addAll(sites.getClosedSites());
                moduleDemos.setAdapter(sAdapter);
                break;

            case R.id.v_labours:
                moduleName.setText(moduleNames[3]);
                sAdapter.clear();
                sAdapter.addAll(labours);
                moduleDemos.setAdapter(sAdapter);
                activityName = "labour";
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //Toast.makeText(this,"Item Selected: "+i,Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> sAdapter = (ArrayAdapter<String>) adapterView.getAdapter();
        String name = sAdapter.getItem(i);

        Log.i("ListItem","name :"+name + " ActivityName :" + activityName);

        Intent intent = null;


        if(activityName.equals("site")) {
            intent = new Intent(this, SiteActivity.class);
            intent.putExtra("name",name);
        }
        else if (activityName.equals("labour")) {
            intent = new Intent(this, LabourActvity.class);
            intent.putExtra("name",name);
        }

        if(intent != null)
            startActivity(intent);
    }
}
