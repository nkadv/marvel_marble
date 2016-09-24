package edureka.sites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class LabourActvity extends AppCompatActivity {

    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_actvity);

        Intent intent = getIntent();
        name = (String)intent.getStringExtra("name");

        TextView tname = (TextView)findViewById(R.id.name);
        TextView tadd = (TextView)findViewById(R.id.address);

        tname.setText("Name: "+ name+"\n\n");
        tadd.setText("Address: \n" +
                      "537 C/61 Sherwani Nagar Sitapur Road Lucknow UP\n\n");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.labour_menu, menu);
        return true;
    }
}
