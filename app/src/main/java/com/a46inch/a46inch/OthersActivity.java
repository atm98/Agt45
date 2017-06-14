package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OthersActivity extends AppCompatActivity {
    public ListView OthersList;
    public String[] Others = {"Carbos","Testoterone Booster","Other Essentials"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview, Others);
        OthersList = (ListView) findViewById(R.id.OTHERSList);
        OthersList.setAdapter(adapter);
    }
}
