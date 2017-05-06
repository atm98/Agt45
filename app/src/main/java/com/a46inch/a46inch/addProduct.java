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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addProduct extends AppCompatActivity {

    private static final String TAG ="addProduct" ;
    private EditText ProdName;
    private Button AddProdpic;
    private EditText ProdPrice;
    private EditText ProdDesc;
    private EditText ProdSeller;
    private Button SaveProductData;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private StorageReference mStorageRef;
    private ProgressDialog progress;
    private static final int GALLERY_INTENT=2;
    private String Productname;
    private String Productdescription;
    private Integer Productprice;
    private FirebaseAuth mAuth;

    private String Productseller;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ProdName = (EditText) findViewById(R.id.Pname); //Product name
        ProdPrice = (EditText) findViewById(R.id.Pprice); //Product price
        ProdDesc = (EditText) findViewById(R.id.Pdescription); //Product description
        ProdSeller = (EditText) findViewById(R.id.Pseller); //Seller name
        AddProdpic = (Button) findViewById(R.id.B9ppic); //Product picture
        SaveProductData = (Button) findViewById(R.id.B10saveproduct); //Save Product Data
        mAuth = FirebaseAuth.getInstance(); //get current FirebaseAuth instance
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //get current FirebaseDatabase instance
        myRef = mFirebaseDatabase.getReference(); //get current reference for FirebaseDatabase instance
        mStorageRef = FirebaseStorage.getInstance().getReference(); //get current FirebaseStorage 
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
        AddProdpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				//if the AddProdpic Button is clicke do this
                Intent intent = new Intent(Intent.ACTION_PICK); //start ACTION_PICK intent
                intent.setType("image/*"); //show only images
                startActivityForResult(intent, GALLERY_INTENT); //move to Gallary intent
            }
        });
        SaveProductData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productname = ProdName.getText().toString();
                Productdescription = ProdDesc.getText().toString();
                Productprice = Integer.parseInt(ProdPrice.getText().toString());
                Productseller = ProdSeller.getText().toString();
                ProductInfo Product = new ProductInfo(Productname, Productprice, Productdescription, Productseller, myRef.getKey());
                myRef.child("ProductList").child(myRef.getKey()).setValue(Product);
                progress.setMessage("Uploading");
                progress.show();
                toastMessage("New Information has been saved.");
                progress.dismiss();


                startActivity(new Intent(addProduct.this, Adminhome.class));

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//revices result from startActivityForResult im form of requestCode,resultCode and data
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){ //if image selected with no errors
            progress.setMessage("Uploading");
            progress.show();
            final Uri uri = data.getData(); //geting Uri from data(i.e image)
            final StorageReference childPath = mStorageRef.child("Products").child(myRef.getKey()).child(uri.getLastPathSegment()); //initialize FirebaseStorage reference childpath
            childPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { //puts a file to the childpath reference of FirebaseStorage
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(addProduct.this,"UPLOAD DONE",Toast.LENGTH_LONG).show();//if the upload is successfull the show the message
                    progress.dismiss(); //end progress

                }
            });


        }
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
            mAuth.removeAuthStateListener(mAuthListener);// at the end of the activity end thr AuthStateListener
        }
    }
    private void toastMessage(String message){ // this methord is used to display toast messages
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show(); 
    }
}


