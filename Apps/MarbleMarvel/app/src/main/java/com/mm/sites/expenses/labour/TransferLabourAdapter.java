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
 * Created by nayerram on 29-10-2016.
 */
public class TransferLabourAdapter extends ArrayAdapter<Labour> {

    public TransferLabourAdapter(Context context, ArrayList<Labour> labours) {
        super(context, 0,labours);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Labour labour = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tl_list_layout, parent, false);
        }
        // Lookup view for data population

        TextView id = (TextView) convertView.findViewById(R.id.tl_labour_id);
        TextView name = (TextView) convertView.findViewById(R.id.tl_labour_name);


        id.setText(labour.id.toString());
        name.setText(labour.name);
        // Return the completed view to render on screen
        return convertView;
    }
}
