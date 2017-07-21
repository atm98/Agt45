package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a46inch.a46inch.CustomAdapters.customAdapter;
import com.a46inch.a46inch.Firebase.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProductListActivity extends AppCompatActivity {
    private Query db;
    private FirebaseHelper helper;
    private ListView lv;
    private String catagory;
    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Intent list = this.getIntent();
        catagory = list.getStringExtra("CATAGORY");

        db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://inch-bd53c.firebaseio.com/").child("Products").orderByChild("pcatagory").equalTo(catagory);

        helper=new FirebaseHelper(db);

        lv = (ListView) findViewById(R.id.lv);
        adapter =new customAdapter(this,helper.Populateall());
        lv.setAdapter(adapter);


    }
}
