package com.mm.labours;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mm.entry.R;
import com.mm.storage.local.db.labour.LabourStorage;

import java.util.Calendar;
import java.util.Locale;

public class AddLabourActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    EditText id,name,fName,place,doj,rate;
    Button bSubmit;
    Labour labour;

    private LabourStorage lStorage;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_labour);

        id = (EditText)findViewById(R.id.al_id);
        name = (EditText)findViewById(R.id.al_name);
        fName = (EditText)findViewById(R.id.al_father_name);
        place = (EditText)findViewById(R.id.al_place);

        doj = (EditText)findViewById(R.id.al_doj);
        doj.setOnClickListener(this);
        calendar = Calendar.getInstance(Locale.getDefault());
        setupDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

        rate = (EditText)findViewById(R.id.al_rate);

        bSubmit = (Button)findViewById(R.id.al_submit);
        bSubmit.setOnClickListener(this);


        lStorage = new LabourStorage();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.al_submit) {
            labour = new Labour();

            if (checkMandatoryFields())
            {
                validateFileds();
                Log.i("", "reading id");
                //  TODO: Check Return Value
                if (!isEditTextEmpty(id)) {
                    labour.id = Integer.parseInt(id.getText().toString());
                }

                labour.name = name.getText().toString();
                labour.fathersName = fName.getText().toString();
                labour.place = place.getText().toString();
                labour.doj = place.getText().toString();
                labour.doj = doj.getText().toString();
                Log.i("add_labour", "reading rate");
                if (!isEditTextEmpty(rate)) {
                    labour.rate = Double.parseDouble(rate.getText().toString());
                }
                //  TODO: Photo URL, Actaul Photo
                labour.photo = "";

                Toast.makeText(getApplicationContext(),"Saving to DB... ",Toast.LENGTH_SHORT).show();
                lStorage.persistLabour(getApplicationContext(),labour);
                startActivity(new Intent(getApplicationContext(),com.mm.labours.ViewLaboursActivity.class));
            }
        }else if (view.getId() == R.id.al_doj){
            //Show Date Picker Dialog
            showDatePickerDialog();
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

        /*id,name,doj,rate*/
        if( isEditTextEmpty(id)){
            Toast.makeText(this,"Enter Mandatory Filed (ID)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(name)){
            Toast.makeText(this,"Enter Mandatory Filed (Name)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(doj)){
            Toast.makeText(this,"Enter Mandatory Filed (Date)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(rate)){
            Toast.makeText(this,"Enter Mandatory Filed (Rate)",Toast.LENGTH_LONG).show();
            bRet = false;
        }
        return bRet;

    }
    private boolean validateFileds(){
        //  TODO: Add Validation Logic
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        setupDate(dayOfMonth, monthOfYear, year);
    }

    private void setupDate(int day, int month, int year) {
        doj.setText(
                String.valueOf(day) + "/" +
                        String.valueOf(month + 1) + "/" +
                        String.valueOf(year)
        );
    }

    private void showDatePickerDialog() {
        calendar = Calendar.getInstance(Locale.getDefault());
        datePickerDialog = new DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
                );
        datePickerDialog.show();
    }
}
