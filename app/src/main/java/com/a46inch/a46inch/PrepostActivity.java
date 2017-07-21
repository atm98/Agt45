package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        PrepostworkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[0]);
                    startActivity(ListIntent);
                }
                else if(position == 1){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[1]);
                    startActivity(ListIntent);
                }
                else if(position == 2){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[2]);
                    startActivity(ListIntent);

                }
                else if(position == 3){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[3]);
                    startActivity(ListIntent);
                }
                else if(position == 4){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[4]);
                    startActivity(ListIntent);
                }
                else if(position == 5){
                    Intent ListIntent = new Intent(PrepostActivity.this, ProductListActivity.class);
                    ListIntent.putExtra("CATAGORY",Prepostwork[5]);
                    startActivity(ListIntent);
                }
            }
        });
        PrepostworkList.setAdapter(adapter);
    }
}

