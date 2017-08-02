package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.a46inch.a46inch.CustomAdapters.customAdapter;
import com.a46inch.a46inch.Firebase.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProductListActivity extends AppCompatActivity {
    private DatabaseReference db;
    private FirebaseHelper helper;
    private ListView lv;
    private String catagory;
    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        lv = (ListView) findViewById(R.id.lv);
        db= FirebaseDatabase.getInstance().getReference().child("Products");

        helper=new FirebaseHelper(db);


        adapter =new customAdapter(this,helper.Populateall());

        lv.setAdapter(adapter);



    }
}
