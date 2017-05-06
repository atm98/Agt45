package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// Page for admin only
public class Adminhome extends AppCompatActivity {
    private Button AddProduct; //declare AddProduct Button
    private Button DelProduct; //declare DelProduct Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        AddProduct = (Button) findViewById(R.id.B8add); //initialize AddProduct Button
        DelProduct = (Button) findViewById(R.id.B7del); //initialize DelProduct Button
        AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				//if AddProduct button Clicked do this
                finish();
                startActivity(new Intent(Adminhome.this,addProduct.class)); //create intent for next activity
            }
        });
        DelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				//if DelProduct button Clicked do this
                finish();
                startActivity(new Intent(Adminhome.this,delProduct.class)); //create intent for next activity
            }
        });

    }
}
