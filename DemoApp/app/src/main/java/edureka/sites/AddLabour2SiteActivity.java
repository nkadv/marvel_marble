package edureka.sites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddLabour2SiteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    AutoCompleteTextView cLabourAC;
    ListView lLabours;
    ArrayAdapter<String> cLaboursAA;
    ArrayAdapter<String> sLaboursAA;
    ArrayList<String> sLabourList;
    Button bAdd;
    Sites sites;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_labour2_site);

        cLabourAC = (AutoCompleteTextView)findViewById(R.id.chooseLabour);
        lLabours = (ListView)findViewById(R.id.labours_list);
        bAdd = (Button)findViewById(R.id.bAdd);

        Intent intent = getIntent();
        name = (String)intent.getStringExtra("name");

        cLaboursAA = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        cLaboursAA.add("avinash");
        cLaboursAA.add("ankur");
        cLaboursAA.add("ramesh");
        cLaboursAA.add("suresh");
        cLaboursAA.add("sravan singh");
        cLaboursAA.add("rajesh gupta");
        cLaboursAA.add("vikram bheev");
        cLaboursAA.add("rajesh gupta");
        cLaboursAA.add("parineesh Gaur");
        cLaboursAA.add("prakash Iyer");
        cLaboursAA.add("Sahib kaur");
        cLaboursAA.add("sravan singh");
        cLaboursAA.add("rajesh gupta");
        cLaboursAA.add("vikram bheev");
        cLaboursAA.add("rajesh gupta");

        cLabourAC.setAdapter(cLaboursAA);
        cLabourAC.setThreshold(1);
        cLabourAC.setOnItemClickListener(this);

        sLaboursAA =  new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
        lLabours.setAdapter(sLaboursAA);
        sLabourList = new ArrayList<String>();

            bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (getApplicationContext(),AddSiteActivity.class);
                intent = new Intent(getApplicationContext(), SiteActivity.class);
                intent.putExtra("name",name);
                if (sLabourList.size() !=0)
                    intent.putExtra("labours",sLabourList);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sLaboursAA.add(cLaboursAA.getItem(position));
        sLabourList.add(cLaboursAA.getItem(position));
        lLabours.setAdapter(sLaboursAA);
        Toast.makeText(this,"Item Click",Toast.LENGTH_SHORT).show();
        cLabourAC.setText("");
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        sLaboursAA.add(cLaboursAA.getItem(position));
//        lLabours.setAdapter(sLaboursAA);
//        Toast.makeText(this,"Item Selected",Toast.LENGTH_SHORT).show();
//        cLabourAC.clearComposingText();
//
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
