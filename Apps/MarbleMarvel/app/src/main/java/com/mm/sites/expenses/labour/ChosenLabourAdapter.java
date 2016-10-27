package com.mm.sites.expenses.labour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mm.entry.R;
import com.mm.labours.Labour;

import java.util.ArrayList;

/**
 * Created by nayerram on 07-10-2016.
 */
public class ChosenLabourAdapter extends ArrayAdapter<Labour> {

    public ChosenLabourAdapter(Context context, ArrayList<Labour> labours) {
        super(context, 0,labours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Labour labour = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.labour_list_layout, parent, false);
        }
        // Lookup view for data population

        TextView name = (TextView) convertView.findViewById(R.id.ll_name);
        TextView fname = (TextView) convertView.findViewById(R.id.ll_fname);
        TextView id = (TextView) convertView.findViewById(R.id.ll_id);
        TextView place = (TextView) convertView.findViewById(R.id.ll_place);

        name.setText(labour.name);
        fname.setText(labour.fathersName);
        id.setText(labour.id.toString());
        place.setText(labour.place);
        // Return the completed view to render on screen
        return convertView;
    }
}
