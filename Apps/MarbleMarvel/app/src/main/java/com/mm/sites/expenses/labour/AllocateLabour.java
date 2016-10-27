package com.mm.sites.expenses.labour;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.labours.Labour;
import com.mm.sites.Site;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.labour.LabourStorage;
import com.mm.storage.local.db.sites.allocations.labour.SiteLabourStorage;

import java.util.ArrayList;
import java.util.Date;

public class AllocateLabour extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Integer siteId;

    SimpleCursorAdapter suggestionListAdapter;
    AutoCompleteTextView acvSearchLabour;
    ListView lvChosenLabour;
    ChosenLabourAdapter chosenLabourAdapter;
    ArrayList<Labour> chosenLabourList;
    Button btAdd;
    LabourStorage lStorage;
    SiteLabourStorage slStorage;

    SimpleCursorAdapter scaSearchLabour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_labour);

        Intent intent = getIntent();
        siteId = (Integer) intent.getIntExtra("site_id",0);
        Toast.makeText(this,"SiteId: "+ siteId, Toast.LENGTH_SHORT).show();

        acvSearchLabour=(AutoCompleteTextView)findViewById(R.id.alloc_labour_choose);
        acvSearchLabour.setThreshold(1);
        acvSearchLabour.setOnItemClickListener(this);

        lStorage = new LabourStorage();
        slStorage = new SiteLabourStorage();

        populateSuggestionList("");

         acvSearchLabour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                populateSuggestionList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                populateSuggestionList(editable.toString());
            }
        });

        lvChosenLabour = (ListView)findViewById(R.id.alloc_labour_list);
        chosenLabourList = new ArrayList<Labour>();
        chosenLabourAdapter = new ChosenLabourAdapter(this,chosenLabourList);


        btAdd = (Button)findViewById(R.id.alloc_labour_add);
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alloc_labour_add:
                Toast.makeText(this,"Num Of Labours: "+ chosenLabourAdapter.getCount(),Toast.LENGTH_SHORT).show();

                ArrayList<Integer> labours = new ArrayList<Integer>();
                for (int i=0;i<chosenLabourAdapter.getCount();i++){
                    labours.add(chosenLabourAdapter.getItem(i).id);
                }
                slStorage.AddLabours(this,labours,siteId,new Date("12/12/2016"));

                Intent intent = new Intent(this,com.mm.sites.SiteActivity.class);
                Site site = new Site();
                site.id = siteId;
                intent.putExtra("site",site);
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Cursor c = ((SimpleCursorAdapter)adapterView.getAdapter()).getCursor();
        c.moveToPosition(position);
        Labour labour = new Labour();
        labour.id = c.getInt(1);  //TODO: Add Comments
        labour.name =  c.getString(1);
        labour.fathersName = c.getString(2);
        labour.place = c.getString(3);
        labour.doj = c.getString(4);
        labour.rate = c.getDouble(5);

        chosenLabourList.add(labour);
        lvChosenLabour.setAdapter(chosenLabourAdapter);
        acvSearchLabour.setText("");
    }

    private void populateSuggestionList(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = lStorage.getAllLabours(this);

            if (cursor != null && cursor.getCount() >0) {
                String[] from = new String[]{DBSchema.Labours.NAME, DBSchema.Labours.LABOUR_ID,
                        DBSchema.Labours.FATHER_NAME, DBSchema.Labours.PLACE};
                int[] to = new int[]{R.id.ll_name, R.id.ll_id, R.id.ll_fname, R.id.ll_place};
                scaSearchLabour = new SimpleCursorAdapter(this, R.layout.labour_list_layout, cursor, from, to, 0);
                acvSearchLabour.setAdapter(scaSearchLabour);
            }
        }
        else{
            cursor = lStorage.getLabours(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());

            if (cursor != null) {
           // if (cursor != null && cursor.getCount() >0) {
                String[] from = new String[]{DBSchema.Labours.NAME, DBSchema.Labours.LABOUR_ID,
                        DBSchema.Labours.FATHER_NAME, DBSchema.Labours.PLACE};
                int[] to = new int[]{R.id.ll_name, R.id.ll_id, R.id.ll_fname, R.id.ll_place};
                scaSearchLabour = new SimpleCursorAdapter(this, R.layout.labour_list_layout, cursor, from, to, 0);
                acvSearchLabour.setAdapter(scaSearchLabour);
            }
        }
    }
}
