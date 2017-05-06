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

public class AddUserinfo extends AppCompatActivity {
    private static final String TAG = "AddUserinfo";

    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText name,dob,phone,address;
    private Button saveData;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private StorageReference mStorageRef;
    private Button savePic;

    private static final int GALLERY_INTENT=2;

    private String userID;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_userinfo);
        name = (EditText) findViewById(R.id.name); //name of the user
        dob = (EditText) findViewById(R.id.dateobirth); //date of birth of the user
        phone = (EditText) findViewById(R.id.Phonenum); //phone number of the user
        address = (EditText) findViewById(R.id.Address); //address of the user
        saveData = (Button) findViewById(R.id.B4save); //Button For saving data to Firebasedatabase
        mAuth = FirebaseAuth.getInstance(); //initalize instance for FirebaseAuth
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //initalize instance for FirebaseDatabase
        myRef = mFirebaseDatabase.getReference(); //initalize reference for FirebaseDatabase
        mStorageRef = FirebaseStorage.getInstance().getReference(); //initalize reference for FirebaseStorage
        savePic = (Button) findViewById(R.id.B6save); //initalize Button to upload image
        progress = new ProgressDialog(this); //initalize Progress bar



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
        savePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				//if savePic Button is clicked do this
                Intent intent = new Intent(Intent.ACTION_PICK); //create ACTION_PICK intent
                intent.setType("image/*"); //Show only images
                startActivityForResult(intent,GALLERY_INTENT); //start the Gallary intent

            }
        });
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				//if save data is clicked do this
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
		//the following method recievers three inputs from the startActivityForResult method
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){ //if the requestCode is equal to the gallart intent code as well as the resultCode is ok then
			
            progress.setMessage("Uploading"); 
            progress.show();
            final Uri uri = data.getData(); //initalize Uri to recievers the path of the data (i.e image) selected

            FirebaseUser User = mAuth.getCurrentUser(); //get instance of the current user that is signed in
            userID=User.getUid(); //gets the unique user id of the currently singed in user
            final StorageReference childPath = mStorageRef.child("Users").child(userID).child(uri.getLastPathSegment()); //creating a FirebaseStorage reference path
            childPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { // adds file path to the childpath FirebaseStorage reference
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddUserinfo.this,"UPLOAD DONE",Toast.LENGTH_LONG).show(); //after upload is success show the following message
                    progress.dismiss(); //end progress 



                }
            });


        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); // at the start of the activity Adds a listener that will be called when the connection becomes authenticated or unauthenticated.
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener); //at the end of the activity removes the AuthStateListener
        }
    }
    private void toastMessage(String message){ // this method is used tod display toast Messages
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
