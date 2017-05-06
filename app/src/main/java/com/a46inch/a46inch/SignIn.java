package com.a46inch.a46inch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "SignIn";
    private FirebaseAuth mAuth; //declare FirebaseAuth object
    private EditText editTextEmail; //declare editText user email
    private EditText editTextPassword; //declare editText user password
    private Button buttonSignIn; //declare Button for Signing in
    private ProgressDialog progressDialog; // declareProgress bar
    
   


    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextEmail = (EditText) findViewById(R.id.editemail); //initialize email of the user
        editTextPassword = (EditText) findViewById(R.id.editpassword); //initialize password of the user
        buttonSignIn = (Button) findViewById(R.id.B2Sign); //initialize Sign in button
        mAuth = FirebaseAuth.getInstance(); //initialize FirebaseAuth instance
        
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        progressDialog = new ProgressDialog(this); //initialize progressDialog 
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == buttonSignIn){
                    signIn(editTextEmail,editTextPassword); // if button pressed run signIn methord
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); //at the start of the activity Adds a listener that will be called when the connection becomes authenticated or unauthenticated.
		
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener); //removes the AuthStateListener at the end of the activity
        }
    }
    public void signIn(EditText mail, EditText passwd){  //SignIn method
        String email = mail.getText().toString().trim(); //converting editText email to string
        String password  = passwd.getText().toString().trim(); //converting editText password to string
        mAuth.signInWithEmailAndPassword(email, password) //signInWithEmailAndPassword accepts email and password as strings
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignIn.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
							//if there is a successful sign in then finish this activity and start next activity
                            finish();


                            startActivity(new Intent(getApplicationContext(),Home.class));

                        }

                        // ...
                    }
                });
    }
    }
