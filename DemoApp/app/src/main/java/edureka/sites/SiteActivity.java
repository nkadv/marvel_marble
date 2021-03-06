package edureka.sites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SiteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String name;
    ArrayList<String> default_labours;
    ArrayList<String> select_labours;
    ArrayAdapter<String> s_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        Intent intent = getIntent();
        name = (String)intent.getStringExtra("name");
        select_labours = (ArrayList<String>)intent.getSerializableExtra("labours");

        TextView tname = (TextView)findViewById(R.id.name);
        TextView tadd = (TextView)findViewById(R.id.address);

        tname.setText("Name: "+ name+"\n\n");
        tadd.setText("Address: \n" +
                "HSR Sector 3 Near BDA Complex Bangalore -560102\n");

        ListView lview = (ListView)findViewById(R.id.labourlist);
        String labours[]={
                "Ramesh",
                "Suresh",
                "Sravan Singh",
                "Rajesh Gupta",
                "Vikram Bheev",
                "Sanjeev"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adapter.addAll(labours);

        //lview.setAdapter(adapter);
        if(select_labours != null) {
            if (select_labours.size() > 0)
                s_adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
                s_adapter.addAll(select_labours);
                lview.setAdapter(s_adapter);
            lview.setOnItemClickListener(this);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.site_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.expense:
                Toast.makeText(this,"Adding Expense...",Toast.LENGTH_SHORT);
                break;
            case R.id.labour:
                //Toast.makeText(this,"Editing Labour Details...",Toast.LENGTH_SHORT);

                Intent intent = new Intent (this,AddLabour2SiteActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
            case R.id.reports:
                Toast.makeText(this,"Generating Reports...",Toast.LENGTH_SHORT);

                break;
            case R.id.money:
                Toast.makeText(this,"Allocating Money...",Toast.LENGTH_SHORT);

                break;
            case R.id.material:
                Toast.makeText(this,"Adding Material...",Toast.LENGTH_SHORT);
                break;

        }

        Toast.makeText(this,"Adding Material...",Toast.LENGTH_SHORT);

        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //Toast.makeText(this,"Item Selected: "+i,Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> sAdapter = (ArrayAdapter<String>) adapterView.getAdapter();
        String name = sAdapter.getItem(i);


        Intent intent = null;


        intent = new Intent(this, LabourActvity.class);
        intent.putExtra("name",name);


        if(intent != null)
        startActivity(intent);
    }

}
