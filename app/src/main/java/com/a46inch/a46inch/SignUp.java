package com.a46inch.a46inch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignIn";
    private FirebaseAuth mAuth;
    private EditText SeditTextEmail;
    private EditText SeditTextPassword;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button SbuttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SeditTextEmail = (EditText) findViewById(R.id.editemailSup); //Edit text for Email of user
        SeditTextPassword = (EditText) findViewById(R.id.editpasswordSup); //Edit text for Password of the user
        SbuttonSignUp = (Button) findViewById(R.id.B3Sign); //SignUp Button
        mAuth = FirebaseAuth.getInstance(); // mAuth is the FireAuth instance
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
        SbuttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == SbuttonSignUp){
                    createAccount(SeditTextEmail,SeditTextPassword);
                }
            }
        }); // onClick methord to begin the Account Registeration process
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); // on start of activity Adds a listener that will be called when the connection becomes authenticated or unauthenticated.
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener); //after the activity is stopped it removes the AuthStateListener
        }
    }
    public void createAccount(EditText mail,EditText passwd){
        String email = mail.getText().toString().trim(); //converts the edittext email to string
        String password  = passwd.getText().toString().trim(); //converts the edittext password to string
        mAuth.createUserWithEmailAndPassword(email, password)  // methord to create account using strig email and password
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
							//if the the sign up is success then finish the activity and start the next one
                            finish();
                            startActivity( new Intent(getApplicationContext(),AddUserinfo.class));
                        }

                        // ...
                    }
                });
    }


}
