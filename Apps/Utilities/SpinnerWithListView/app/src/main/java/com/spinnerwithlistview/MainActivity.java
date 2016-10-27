package com.spinnerwithlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Spinner;

import com.spinnerwithlistview.adapter.AddressAdapter;
import com.spinnerwithlistview.adapter.CityAdapter;
import com.spinnerwithlistview.model.Address;
import com.spinnerwithlistview.model.City;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private List<Address> addressEntityList = new ArrayList<Address>();
    private List<City> cityEntityList = new ArrayList<City>();
    private ListView listView;
    private AddressAdapter adapter;
    private CityAdapter cityAdapter;
    private Spinner citySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citySpinner = (Spinner)findViewById(R.id.citySpinner);
        listView = (ListView) findViewById(R.id.address_listview);
        cityAdapter = new CityAdapter(this,android.R.layout.simple_spinner_dropdown_item,loadDummyCities());
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(this);
        loadDummyAddress();
        adapter = new AddressAdapter(this, addressEntityList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<City> loadDummyCities(){
        cityEntityList = new ArrayList<City>();

        City city1 = new City();
        city1.setId(1);
        city1.setCity("Kochi");
        cityEntityList.add(city1);
        City city2 = new City();
        city2.setId(2);
        city2.setCity("Bangalore");
        cityEntityList.add(city2);
        City city3 = new City();
        city3.setId(3);
        city3.setCity("Delhi");
        cityEntityList.add(city3);

        return cityEntityList;
    }

    private List<Address> loadDummyAddress(){

        addressEntityList = new ArrayList<Address>();
        Address address1 = new Address();
        address1.setId(1);
        address1.setCityId(1);
        address1.setArea("Kalamassery");
        address1.setBuildingName("Kinfra");
        address1.setStreet("2nd");
        addressEntityList.add(address1);

        Address address2 = new Address();
        address2.setId(2);
        address2.setCityId(2);
        address2.setArea("Banaswadi");
        address2.setBuildingName("Sharmi");
        address2.setStreet("2nd Cross");
        addressEntityList.add(address2);

        Address address3 = new Address();
        address3.setId(3);
        address3.setCityId(2);
        address3.setArea("MG Road");
        address3.setBuildingName("Carlton");
        address3.setStreet("Church Street");
        addressEntityList.add(address3);

        Address address4 = new Address();
        address4.setId(4);
        address4.setCityId(1);
        address4.setArea("Thrissur");
        address4.setBuildingName("New");
        address4.setStreet("Vatanappilly");
        addressEntityList.add(address4);

        return addressEntityList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        City city = cityAdapter.getItem(position);

        adapter.getFilter().filter(Long.toString(city.getId()),new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int count) {

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
