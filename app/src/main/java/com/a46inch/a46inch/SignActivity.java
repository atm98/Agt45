package com.a46inch.a46inch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//this activity is there so that the user can choose whether he/she wishes to singin or sign up
public class SignActivity extends AppCompatActivity {
    private Button SignIn; //declare Singin button
    private Button SignUp; //declare Singup button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        SignIn =(Button) findViewById(R.id.B1); //initialize SignIn button
        SignUp =(Button) findViewById(R.id.B2); //initialize SignUp button


        SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
				//if SignIn Button is clicked do this
                Intent i1 = new Intent(SignActivity.this, SignIn.class); //intent for next activity
                startActivity(i1);
            }

        });
        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
				//if SignUp Button is clicked do this
                Intent i1 = new Intent(SignActivity.this, SignUp.class); //intent for next activity
                startActivity(i1);
            }

        });
    }
}
