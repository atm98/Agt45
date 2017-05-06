package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminhome extends AppCompatActivity {
    private Button AddProduct;
    private Button DelProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        AddProduct = (Button) findViewById(R.id.B8add);
        DelProduct = (Button) findViewById(R.id.B7del);
        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Adminhome.this,addProduct.class));
            }
        });
        DelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Adminhome.this,delProduct.class));
            }
        });

    }
}
