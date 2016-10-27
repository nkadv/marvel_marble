package com.spinnerwithlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.spinnerwithlistview.R;
import com.spinnerwithlistview.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Technovibe on 07-04-2015.
 */
public class AddressAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater mLayoutInflater;
    private List<Address> addressList;
    private List<Address> addressFilterList;
    private AddressFilter addressFilter;
    private Context context;

    public AddressAdapter(Context context, List data){
        addressList = data;
        addressFilterList=data;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Address getItem(int position) {
        return addressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View updateView;
        ViewHolder viewHolder;
        if (view == null) {
            updateView = mLayoutInflater.inflate(R.layout.address_listitem, null);
            viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) updateView.findViewById(R.id.nameTV);
            viewHolder.tvArea = (TextView) updateView.findViewById(R.id.areaTV);
            viewHolder.tvStreet = (TextView) updateView.findViewById(R.id.streetTv);

            updateView.setTag(viewHolder);

        } else {
            updateView = view;
            viewHolder = (ViewHolder) updateView.getTag();
        }

        final Address item = getItem(position);

            viewHolder.tvName.setText(item.getBuildingName());
            viewHolder.tvArea.setText(item.getArea());
            viewHolder.tvStreet.setText(String.valueOf(item.getStreet()));


        return updateView;

    }

    @Override
    public Filter getFilter() {
        if (addressFilter == null) {
            addressFilter = new AddressFilter();
        }
        return addressFilter;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvArea;
        TextView tvStreet;

    }

    private class AddressFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            long cityId= Long.parseLong(constraint.toString());
            FilterResults results = new FilterResults();

            if (cityId > 0) {
                ArrayList<Address> filterList = new ArrayList<Address>();
                for (int i = 0; i < addressFilterList.size(); i++) {

                    if ( (addressFilterList.get(i).getCityId() )== cityId) {

                        Address address = addressFilterList.get(i);
                        filterList.add(address);
                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {

                results.count = addressFilterList.size();
                results.values = addressFilterList;

            }
            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            addressList = (ArrayList<Address>)results.values;
            notifyDataSetChanged();
        }
    }

}

