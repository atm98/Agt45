package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        ProteinList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[0]);
                    startActivity(ListIntent);
                }
                else if(position == 1){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[1]);
                    startActivity(ListIntent);
                }
                else if(position == 2){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[2]);
                    startActivity(ListIntent);
                    
                }
                else if(position == 3){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[3]);
                    startActivity(ListIntent);
                }
                else if(position == 4){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[4]);
                    startActivity(ListIntent);
                }
                else if(position == 5){
                    Intent ListIntent = new Intent(ProteinsActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Protein[5]);
                    startActivity(ListIntent);
                }
            }
        });
        ProteinList.setAdapter(adapter);


    }
}
