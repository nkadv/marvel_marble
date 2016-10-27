package com.ms.kanth.exploreviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_eLV:
                startActivity(new Intent(this,ExpandableLVActvity.class));
                break;
            case R.id.menu_eT:
                //Toast.makeText(this,"Editing Labour Details...",Toast.LENGTH_SHORT);
                startActivity(new Intent(this,com.ms.kanth.exploreviews.EditBox.EditTextActivity.class));
                break;
            case R.id.menu_date:
                startActivity(new Intent(this,com.ms.kanth.utils.date.DateActivity.class));
                break;

            case R.id.menu_cbList:
                startActivity(new Intent(this,com.ms.kanth.utils.cblist.CbListActivity.class));
                break;
        }
        return true;
    }

}
