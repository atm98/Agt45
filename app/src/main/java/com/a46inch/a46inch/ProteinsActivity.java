package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProteinsActivity extends AppCompatActivity {
    public ListView ProteinList;
    public String[] Protein = {"Whey Proteins","Whey Isolates","Casein Proteins","Mass Gainers","Lean Mass Gainers","Protein Bars"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proteins);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview, Protein);
        ProteinList = (ListView) findViewById(R.id.proteinList);
        ProteinList.setAdapter(adapter);


    }
}
