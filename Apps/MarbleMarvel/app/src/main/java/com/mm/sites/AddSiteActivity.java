package com.mm.sites;

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
import com.mm.storage.local.db.sites.SiteStorage;

import java.util.Calendar;
import java.util.Locale;

public class AddSiteActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    EditText name,location,startDate;
    Button bSubmit;
    SiteStorage sStorage;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        name = (EditText)findViewById(R.id.as_name);
        location = (EditText)findViewById(R.id.as_location);
        startDate = (EditText)findViewById(R.id.as_date);
        startDate.setOnClickListener(this);

        bSubmit = (Button)findViewById(R.id.as_submit);
        bSubmit.setOnClickListener(this);

        sStorage = new SiteStorage();

        calendar = Calendar.getInstance(Locale.getDefault());

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        setupDate(day, month, year);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.as_submit){
            Site site = new Site();

            if (checkMandatoryFields()) {
                validateFileds();

                site.name = name.getText().toString();
                site.location = location.getText().toString();
                site.startDate = startDate.getText().toString();
                Toast.makeText(getApplicationContext(),"Saving to DB... ",Toast.LENGTH_SHORT).show();
                sStorage.persistSite(getApplicationContext(),site);

                startActivity(new Intent(getApplicationContext(),com.mm.entry.MainActivity.class));
            }

        }else if (view.getId() == R.id.as_date){
            showDatePickerDialog();
        }
    }

    private boolean isEditTextEmpty(EditText etText) {
        Log.i("add_site","Length: "+ etText.getText().toString().trim().length());
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private boolean checkMandatoryFields(){

        boolean bRet=true;

        /*name,startDate*/
        if( isEditTextEmpty(name)){
            Toast.makeText(this,"Enter Mandatory Filed (ID)",Toast.LENGTH_LONG).show();
            bRet = false;
        }else if (isEditTextEmpty(startDate)){
            Toast.makeText(this,"Enter Mandatory Filed (Name)",Toast.LENGTH_LONG).show();
            bRet = false;
        }
        return bRet;

    }
    private boolean validateFileds(){
        return true;
    }

    private void setupDate(int day, int month, int year) {
        startDate.setText(
                String.valueOf(day) + "/" +
                        String.valueOf(month + 1) + "/" +
                        String.valueOf(year)
        );
    }

    private void showDatePickerDialog() {

        datePickerDialog = new DatePickerDialog
                (
                        this
                        , this
                        , year
                        , month
                        , day
                );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            setupDate(dayOfMonth, monthOfYear, year);
    }
}
