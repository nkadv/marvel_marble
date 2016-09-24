package edureka.sites;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AddSiteActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText name;
    EditText date;
    Sites sites;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private int day, month, year;
    private int hours, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        Intent intent = getIntent();
        sites = (Sites)intent.getSerializableExtra("siteObj");

        int len = sites.getClosedSites().length;
        //Toast.makeText(this,"Len: "+len,Toast.LENGTH_SHORT).show();



        Button bt = (Button) findViewById(R.id.scButton);
        bt.setOnClickListener(this);

        name = (EditText)findViewById(R.id.name);
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(this);


        calendar = Calendar.getInstance(Locale.getDefault());

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        setupDate(day, month, year);


    }

    private void setupDate(int day, int month, int year) {
        date.setText(
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
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.scButton:
                sites.addActiveSite(name.getText().toString());
                Toast.makeText(this,"Adding Site... : "+ name.getText(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent (this,HomeActivity.class));
                break;
            case R.id.date:
                showDatePickerDialog();
                break;

            default:
                break;



        }

    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        setupDate(dayOfMonth, monthOfYear, year);
    }
}
