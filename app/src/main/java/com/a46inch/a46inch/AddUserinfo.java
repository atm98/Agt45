package com.a46inch.a46inch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddUserinfo extends AppCompatActivity {
    private static final String TAG = "AddUserinfo";

    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText name,dob,phone,address;
    private Button saveData;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private StorageReference mStorageRef;
    private Button viewPic;

    private static final int GALLERY_INTENT=2;

    private String userID;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_userinfo);
        name = (EditText) findViewById(R.id.name);
        dob = (EditText) findViewById(R.id.dateobirth);
        phone = (EditText) findViewById(R.id.Phonenum);
        address = (EditText) findViewById(R.id.Address);
        saveData = (Button) findViewById(R.id.B4save);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        viewPic = (Button) findViewById(R.id.B6save);
        progress = new ProgressDialog(this);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }

            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        viewPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);

            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Submit pressed.");
                String uname = name.getText().toString();
                String udob = dob.getText().toString();
                String uphone = phone.getText().toString();
                String uaddress = address.getText().toString();

                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + uname + "\n" +
                        "dob: " + udob + "\n" +
                        "phone number: " + uphone + "\n" +
                        "Address: " + uaddress + "\n"

                );

                //handle the exception if the EditText fields are null
                if (!uname.equals("") && !udob.equals("") && !uphone.equals("") && !uaddress.equals("")) {
                    Userinfo userInformation = new Userinfo(uname,udob,uphone,uaddress);
                    FirebaseUser User = mAuth.getCurrentUser();
                    userID=User.getUid();
                    myRef.child("UserInfo").child(userID).setValue(userInformation);
                    toastMessage("New Information has been saved.");

                    startActivity(new Intent(AddUserinfo.this, Home.class));


                } else
                    toastMessage("Fill out all the fields");
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            progress.setMessage("Uploading");
            progress.show();
            final Uri uri = data.getData();

            FirebaseUser User = mAuth.getCurrentUser();
            userID=User.getUid();
            final StorageReference childPath = mStorageRef.child("Users").child(userID).child(uri.getLastPathSegment());
            childPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddUserinfo.this,"UPLOAD DONE",Toast.LENGTH_LONG).show();
                    progress.dismiss();



                }
            });


        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
