package com.mm.sites.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mm.entry.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nayerram on 14-10-2016.
 */
public class AttendanceAdapter  extends BaseAdapter{

    private LayoutInflater mLayoutInflater;
    private Context context;
    private Date startDate,endDate;
    private Calendar startCal,endCal;

    private ArrayList<AttendanceModel> attendence;
    private SimpleDateFormat sDateFormat;
    ArrayAdapter<CharSequence> spWageAdapter;
    Integer mCount;

    public AttendanceAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;

        sDateFormat = new SimpleDateFormat("dd-MMM-yyyy, EEE"); //12-OCT-2016 Wed

        spWageAdapter = ArrayAdapter.createFromResource(context,
                R.array.wage_multiple, android.R.layout.simple_spinner_item);
        spWageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public boolean setDateRange(Calendar startCal, Calendar endCal){

        boolean bRet = true;
        if(startCal.compareTo(endCal) < 0) {

            this.startCal = startCal;
            this.endCal = endCal;
            attendence = new ArrayList<AttendanceModel>();

            mCount =0;
            do{

                startCal.add(Calendar.DATE,1);
                String day = sDateFormat.format(startCal.getTime());
                AttendanceModel att = new AttendanceModel(day);
                if(day.contains("Sun"))
                    att.setWageMultiplier(0); //1.0x, Position, wage_multiple.xml
                else
                    att.setWageMultiplier(2); //1.5x, Position, wage_multiple.xml
                attendence.add(att);

                mCount +=1;
            }while(! endCal.equals(startCal));
        }else{
            //  TODO: Alert user ??
            bRet = false;
        }

        return bRet;
    }
    @Override
    public int getCount() {

        return attendence.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View updateView;
        ViewHolder viewHolder;
        if (view == null) {
            updateView = mLayoutInflater.inflate(R.layout.attendance_list_layout, null);
            viewHolder = new ViewHolder();

            viewHolder.tvDate = (TextView) updateView.findViewById(R.id.att_list_date);
            viewHolder.spMultFactor = (Spinner) updateView.findViewById(R.id.att_list_mf);
            viewHolder.spMultFactor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // TODO: Tough nut to grasp in first short, preserve understanding with comments :)
                    int pos = (Integer) adapterView.getTag();
                    attendence.get(pos).setWageMultiplier(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            viewHolder.cbPresent = (CheckBox) updateView.findViewById(R.id.att_list_presnt);
            viewHolder.cbPresent.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            int pos = (Integer) compoundButton.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                            attendence.get(pos).setPresent(b);// Set the value of checkbox to maintain its state.
                        }
                    });


            updateView.setTag(viewHolder);
            updateView.setTag(R.id.att_list_mf,viewHolder.spMultFactor);
            updateView.setTag(R.id.att_list_presnt,viewHolder.cbPresent);

        } else {
            updateView = view;
            viewHolder = (ViewHolder) updateView.getTag();
        }

        viewHolder.cbPresent.setTag(position);
        viewHolder.spMultFactor.setTag(position);

        AttendanceModel att = attendence.get(position);

        viewHolder.tvDate.setText(att.getDay());
        viewHolder.cbPresent.setChecked(att.getPresent());

        viewHolder.spMultFactor.setAdapter(spWageAdapter);
        viewHolder.spMultFactor.setSelection(att.getWageMultiplier());

        return updateView;
    }

    static class ViewHolder{
        TextView tvDate;
        Spinner spMultFactor;
        CheckBox cbPresent;

    }
}
