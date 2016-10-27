package com.mm.labours;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.labour.LabourStorage;

public class ViewLaboursActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvLabours;
    EditText etvSearchLabour;
    LabourStorage lStorage;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_labours);


        lStorage = new LabourStorage();
        lvLabours = (ListView) findViewById(R.id.vl_labour_lList);
        etvSearchLabour = (EditText) findViewById(R.id.vl_search_labour);
        pbar = (ProgressBar)findViewById(R.id.vl_pbar);


        populateLabourList("");

        etvSearchLabour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                if (editable.length()  == 2 || editable.length()  == 4 )
//                    Toast.makeText(getApplicationContext(),"Length:"+ editable.length(), Toast.LENGTH_SHORT).show();

                populateLabourList(editable.toString());
            }
        });

        lvLabours.setOnItemClickListener(this);
    }

    private void populateLabourList(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = lStorage.getAllLabours(this);

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Labours.NAME, DBSchema.Labours.LABOUR_ID,
                        DBSchema.Labours.FATHER_NAME, DBSchema.Labours.PLACE};
                int[] to = new int[]{R.id.ll_name, R.id.ll_id, R.id.ll_fname, R.id.ll_place};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.labour_list_layout, cursor, from, to, 0);
                lvLabours.setAdapter(simpleCursorAdapter);
            }
        }
        else{
            cursor = lStorage.getLabours(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());


            if (cursor != null) {
                String[] from = new String[]{DBSchema.Labours.NAME, DBSchema.Labours.LABOUR_ID,
                        DBSchema.Labours.FATHER_NAME, DBSchema.Labours.PLACE};
                int[] to = new int[]{R.id.ll_name, R.id.ll_id, R.id.ll_fname, R.id.ll_place};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.labour_list_layout, cursor, from, to, 0);
                lvLabours.setAdapter(simpleCursorAdapter);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(adapterView.getId()){
            case R.id.vl_labour_lList:


                 /*Launch Labour*/
                Cursor c = ((SimpleCursorAdapter)adapterView.getAdapter()).getCursor();
                c.moveToPosition(position);
                Labour labour = new Labour();
                labour.id = c.getInt(0);
                labour.name =  c.getString(1);
                labour.fathersName = c.getString(2);
                labour.place = c.getString(3);
                labour.doj = c.getString(4);
                labour.rate = c.getDouble(5);

                Intent intent = new Intent(this, com.mm.labours.LabourActivity.class);
                intent.putExtra("labour",labour);
                startActivity(intent);

                Toast.makeText(this,"Launching Labour Id: "+ labour.id, Toast.LENGTH_SHORT).show();

                break;
        }
    }

    // class LoadLabours implements AsyncTask
}
