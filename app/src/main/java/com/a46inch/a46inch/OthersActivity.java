package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        OthersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[0]);
                    startActivity(ListIntent);
                }
                else if(position == 1){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[1]);
                    startActivity(ListIntent);
                }
                else if(position == 2){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[2]);
                    startActivity(ListIntent);

                }
                else if(position == 3){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[3]);
                    startActivity(ListIntent);
                }
                else if(position == 4){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[4]);
                    startActivity(ListIntent);
                }
                else if(position == 5){
                    Intent ListIntent = new Intent(OthersActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Others[5]);
                    startActivity(ListIntent);
                }
            }
        });
        OthersList.setAdapter(adapter);
    }
}
