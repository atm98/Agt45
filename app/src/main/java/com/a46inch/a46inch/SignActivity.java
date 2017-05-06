package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignActivity extends AppCompatActivity {
    private Button SignIn;
    private Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        SignIn =(Button) findViewById(R.id.B1);
        SignUp =(Button) findViewById(R.id.B2);


        SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SignActivity.this, SignIn.class);
                startActivity(i1);
            }

        });
        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SignActivity.this, SignUp.class);
                startActivity(i1);
            }

        });
    }
}
