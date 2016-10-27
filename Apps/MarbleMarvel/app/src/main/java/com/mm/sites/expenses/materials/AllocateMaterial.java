package com.mm.sites.expenses.materials;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.storage.local.db.materials.MaterialStorage;
import com.mm.storage.local.db.sites.allocations.material.SiteMaterialStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AllocateMaterial extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        View.OnClickListener{

    MaterialStorage mStorage;
    SiteMaterialStorage smStorage;
    AutoCompleteTextView mName,mUnits;
    EditText mQuantity,meDate;
    Button bAdd;

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    ArrayList<String> mNameList;
    ArrayList<Integer> mIdList;

    Integer mId,siteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_material);

        Intent intent = getIntent();
        siteId = (Integer) intent.getIntExtra("site_id",0);

        mStorage = new MaterialStorage();
        smStorage = new SiteMaterialStorage();
        mNameList = new ArrayList<String>();
        mIdList = new ArrayList<Integer>();

        populateMaterials();
        mName = (AutoCompleteTextView)findViewById(R.id.ame_mName);
        mName.setAdapter( new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,mNameList));
        mName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter adapter = (ArrayAdapter<String>) adapterView.getAdapter();
                mId = mIdList.get(i);
            }
        });

        mUnits = (AutoCompleteTextView) findViewById(R.id.ame_mUnits);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.material_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUnits.setAdapter(adapter);

        mQuantity = (EditText) findViewById(R.id.ame_mQuantity);

        meDate = (EditText)findViewById(R.id.ame_date);
        calendar = Calendar.getInstance(Locale.getDefault());
        setupDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        meDate.setOnClickListener(this);

        bAdd = (Button) findViewById(R.id.ame_add);
        bAdd.setOnClickListener(this);


    }


    private boolean populateMaterials() {
        Cursor cursor;
        cursor = mStorage.getAllMaterials(this);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                mIdList.add(cursor.getInt(0));
                mNameList.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        return true;
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        setupDate(dayOfMonth, monthOfYear, year);
    }

    private void setupDate(int day, int month, int year) {
        meDate.setText(
                String.valueOf(day) + "/" +
                        String.valueOf(month + 1) + "/" +
                        String.valueOf(year)
        );
    }

    private void showDatePickerDialog() {
        calendar = Calendar.getInstance(Locale.getDefault());
        datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ame_add:
                if(validateFields()) {
                    boolean b = smStorage.AddMaterialExpense(getApplicationContext(), mId,
                            Double.parseDouble(mQuantity.getText().toString())
                            , siteId, meDate.getText().toString());

                    startActivity(new Intent(this, com.mm.sites.SiteActivity.class));
                }
                break;
                    case R.id.ame_date:
                        showDatePickerDialog();
                break;

            default:
                break;
        }
    }

    private boolean validateFields(){
        boolean bRet =true;

        if( isEditTextEmpty(mName)){
            Toast.makeText(this,"Enter Mandatory Filed (Material Name)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(mQuantity)){
            Toast.makeText(this,"Enter Mandatory Filed (Qunatity)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(meDate)){
            Toast.makeText(this,"Enter Mandatory Filed (Date)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(mUnits)){
            Toast.makeText(this,"Enter Mandatory Filed (Units)",Toast.LENGTH_LONG).show();
            bRet = false;
        }
        return bRet;

    }

    private boolean isEditTextEmpty(EditText etText) {
        Log.i("add_labour","Length: "+ etText.getText().toString().trim().length());
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }



}
