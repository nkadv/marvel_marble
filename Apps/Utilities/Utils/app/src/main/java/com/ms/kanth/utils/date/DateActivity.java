package com.ms.kanth.utils.date;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ms.kanth.exploreviews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {

    Calendar calendar;
    int day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);



        calendar = Calendar.getInstance(Locale.getDefault());

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

//        int week_day = calendar.get(Calendar.DAY_OF_WEEK);
//        if(week_day == Calendar.THURSDAY)
//            Toast.makeText(this,"Thursday", Toast.LENGTH_SHORT).show();
//
//        calendar.add(Calendar.DATE,1);
//        week_day = calendar.get(Calendar.DAY_OF_WEEK);
//        if(week_day == Calendar.FRIDAY)
//            Toast.makeText(this,"FRIDAY", Toast.LENGTH_SHORT).show();
//
//        calendar.set(2016,9,1);
//        week_day = calendar.get(Calendar.DAY_OF_WEEK);
//        Toast.makeText(this,"day: "+week_day, Toast.LENGTH_SHORT).show();
//
//
//        calendar.set(2016,11,31);
//        week_day = calendar.get(Calendar.DAY_OF_WEEK);
//        Toast.makeText(this,"day: "+week_day, Toast.LENGTH_SHORT).show();
//
//        calendar.add(Calendar.DATE,1);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//        year = calendar.get(Calendar.YEAR);
//        Toast.makeText(this,"month: "+month + " Day: "+ day+ " Year"+year, Toast.LENGTH_SHORT).show();
//
          Calendar startDate = Calendar.getInstance();
          SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy EEE");
          startDate.set(2016,9,18);

          Calendar endDate = Calendar.getInstance();
          endDate.set(2016,10,13);

          do{

//              month = startDate.get(Calendar.MONTH);
//              day = startDate.get(Calendar.DAY_OF_MONTH);
//              year = startDate.get(Calendar.YEAR);
//              Toast.makeText(this,"month: "+month + " Day: "+ day+ " Year"+year, Toast.LENGTH_SHORT).show();

              Toast.makeText(this,"Date:"+format.format(startDate.getTime()), Toast.LENGTH_SHORT).show();
              startDate.add(Calendar.DATE,1);
          }while(!((startDate.get(Calendar.DAY_OF_MONTH) == endDate.get(Calendar.DAY_OF_MONTH))
                  && (startDate.get(Calendar.MONTH) == endDate.get(Calendar.MONTH))
                  && (startDate.get(Calendar.YEAR)== endDate.get(Calendar.YEAR))));




    }
}
