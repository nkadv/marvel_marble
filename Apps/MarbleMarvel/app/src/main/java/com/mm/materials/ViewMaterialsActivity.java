package com.mm.materials;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mm.entry.R;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.materials.MaterialStorage;

public class ViewMaterialsActivity extends AppCompatActivity {
    ListView lvMaterials;
    EditText etvSearchMaterial;
    MaterialStorage mStorage;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_materials);

        mStorage = new MaterialStorage();
        lvMaterials = (ListView) findViewById(R.id.vm_material_list);
        etvSearchMaterial = (EditText) findViewById(R.id.vm_search_material);
        pbar = (ProgressBar)findViewById(R.id.vm_pbar);

        populateLabourList("");

        etvSearchMaterial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                populateLabourList(editable.toString());
            }
        });
    }


    private void populateLabourList(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = mStorage.getAllMaterials(this);

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Materials.NAME};
                int[] to = new int[]{android.R.id.text1};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, from, to, 0);
                lvMaterials.setAdapter(sCA);
            }
        }
        else{
            cursor = mStorage.getMaterials(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());


            if (cursor != null) {
                String[] from = new String[]{DBSchema.Materials.NAME};
                int[] to = new int[]{android.R.id.text1};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, from, to, 0);
                lvMaterials.setAdapter(sCA);
            }
        }
    }

}
