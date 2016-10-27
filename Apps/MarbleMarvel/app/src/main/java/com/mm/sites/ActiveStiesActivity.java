package com.mm.sites;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.mm.entry.R;
import com.mm.storage.local.db.DBSchema;
import com.mm.storage.local.db.sites.SiteStorage;

public class ActiveStiesActivity extends AppCompatActivity {

    ListView active_s_lv;
    SiteStorage sStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_sties);

        active_s_lv = (ListView)findViewById(R.id.active_s_list);
        sStorage = new SiteStorage();

        populateActiveSites("");
    }

    private void populateActiveSites(String searchString){

        Cursor cursor;

        if(searchString.length() == 0) {
            cursor = sStorage.getActiveSites(this);

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Sites.NAME, DBSchema.Sites.LOCATION};
                int[] to = new int[]{android.R.id.text1,android.R.id.text2};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to, 0);
                active_s_lv.setAdapter(sCA);
            }
        }
        else{
            cursor = sStorage.getSites(this,searchString);
            Log.i("Search","SearchString : "+searchString);
            Log.i("Search","Count"+cursor.getCount());

            if (cursor != null) {
                String[] from = new String[]{DBSchema.Sites.NAME, DBSchema.Sites.LOCATION};
                int[] to = new int[]{android.R.id.text1,android.R.id.text2};
                SimpleCursorAdapter sCA = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to, 0);
                active_s_lv.setAdapter(sCA);
            }
        }
    }
}
