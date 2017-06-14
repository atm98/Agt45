package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrepostActivity extends AppCompatActivity {
    public ListView PrepostworkList;
    public String[] Prepostwork = {"Pre Workout","Cretine","Glutamine","Amino/BCAAs","Workout Essentials","Shakers"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepost);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview, Prepostwork);
        PrepostworkList = (ListView) findViewById(R.id.PREPOSTList);
        PrepostworkList.setAdapter(adapter);
    }
}

