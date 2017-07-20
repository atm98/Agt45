package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a46inch.a46inch.CustomAdapters.customAdapter;
import com.a46inch.a46inch.Firebase.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

public class ProductListActivity extends AppCompatActivity {
    private DatabaseReference db;
    private FirebaseHelper helper;
    private ListView lv;

    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
    }
}
