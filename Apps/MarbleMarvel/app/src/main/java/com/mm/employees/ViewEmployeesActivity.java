package com.mm.employees;

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
import com.mm.storage.local.db.employee.EmployeeStorage;

public class ViewEmployeesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lvEmployees;
    EditText etSearchEmployee;
    EmployeeStorage eStorage;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employees);

        eStorage = new EmployeeStorage();
        lvEmployees = (ListView) findViewById(R.id.ve_employee_lList);
        etSearchEmployee = (EditText) findViewById(R.id.ve_search_employee);
        pbar = (ProgressBar)findViewById(R.id.ve_pbar);


        populateLabourList("");

        etSearchEmployee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()  == 2 || editable.length()  == 4 )
                    Toast.makeText(getApplicationContext(),"Length:"+ editable.length(), Toast.LENGTH_SHORT).show();

                populateLabourList(editable.toString());
            }
        });

        lvEmployees.setOnItemClickListener(this);
    }

    private void populateLabourList(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = eStorage.getAllEmployees(this);

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Employees.EMPNAME};
                int[] to = new int[]{android.R.id.text1};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
                lvEmployees.setAdapter(simpleCursorAdapter);
            }
        }
        else{
            cursor = eStorage.getEmployees(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());


            if (cursor != null) {
                String[] from = new String[]{DBSchema.Employees.EMPNAME};
                int[] to = new int[]{android.R.id.text1};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
                lvEmployees.setAdapter(simpleCursorAdapter);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(adapterView.getId()){
            case R.id.ve_employee_lList:

                 /*Launch Employee*/
                //  TODO:Lanuch Employee
                break;
        }
    }
}

//  TODO: Replace View Labours, Materials, Employees with Fragment ??