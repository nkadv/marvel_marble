package com.ms.kanth.utils.cblist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.kanth.exploreviews.R;

import java.util.ArrayList;
import java.util.List;

public class CbListActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_cb_list);

        listView = (ListView) findViewById(R.id.my_list);
        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
        TextView label = (TextView) v.getTag(R.id.label);
        CheckBox checkbox = (CheckBox) v.getTag(R.id.check);
        Toast.makeText(v.getContext(), label.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();
    }

    private String isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
            return "is checked";
        else
            return "is not checked";
    }

    private List<Model> getModel() {
        list.add(new Model("Linux"));
        list.add(new Model("Windows7"));
        list.add(new Model("Suse"));
        list.add(new Model("Eclipse"));
        list.add(new Model("Ubuntu"));
        list.add(new Model("Solaris"));
        list.add(new Model("Android"));
        list.add(new Model("iPhone"));
        list.add(new Model("Java"));
        list.add(new Model(".Net"));
        list.add(new Model("PHP"));
        list.add(new Model("Linux"));
        list.add(new Model("Windows7"));
        list.add(new Model("Suse"));
        list.add(new Model("Eclipse"));
        list.add(new Model("Ubuntu"));
        list.add(new Model("Solaris"));
        list.add(new Model("Android"));
        list.add(new Model("iPhone"));
        list.add(new Model("Java"));
        list.add(new Model(".Net"));
        list.add(new Model("PHP"));
        return list;
    }
}
