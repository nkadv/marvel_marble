package com.mm.materials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.storage.local.db.materials.MaterialStorage;

public class AddMaterialActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,type,cost;
    AutoCompleteTextView units;
    Button bSubmit;
    Material material;
    private MaterialStorage mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        name = (EditText)findViewById(R.id.am_name);
        units = (AutoCompleteTextView)findViewById(R.id.am_units);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.material_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(adapter);
        units.setThreshold(1);

        cost = (EditText)findViewById(R.id.am_cost);
        bSubmit = (Button)findViewById(R.id.am_submit);
        bSubmit.setOnClickListener(this);

        mStorage = new MaterialStorage();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.am_submit) {
            material = new Material();

            if (checkMandatoryFields())
            {
                validateFields();

                material.name = name.getText().toString();
                material.units = units.getText().toString();
                material.costPerUnit = Double.parseDouble(cost.getText().toString());

                Toast.makeText(getApplicationContext(),"Saving to DB... ",Toast.LENGTH_SHORT).show();
                mStorage.persistMaterial(getApplicationContext(),material);

                startActivity(new Intent(getApplicationContext(),com.mm.materials.ViewMaterialsActivity.class));
            }

        }
    }

    private boolean isEditTextEmpty(EditText etText) {
        Log.i("add_labour","Length: "+ etText.getText().toString().trim().length());

        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean checkMandatoryFields(){

        boolean bRet=true;

        /*name,units,cost*/
        if (isEditTextEmpty(name)){
            Toast.makeText(this,"Enter Mandatory Filed (Name)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(units)){
            Toast.makeText(this,"Enter Mandatory Filed (Units)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(cost)){
            Toast.makeText(this,"Enter Mandatory Filed (Cost)",Toast.LENGTH_LONG).show();
            bRet = false;
        }
        return bRet;

    }
    private boolean validateFields(){
        return true;
    }
}
