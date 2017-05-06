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
        ProdName = (EditText) findViewById(R.id.Pname);
        ProdPrice = (EditText) findViewById(R.id.Pprice);
        ProdDesc = (EditText) findViewById(R.id.Pdescription);
        ProdSeller = (EditText) findViewById(R.id.Pseller);
        AddProdpic = (Button) findViewById(R.id.B9ppic);
        SaveProductData = (Button) findViewById(R.id.B10saveproduct);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
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
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            progress.setMessage("Uploading");
            progress.show();
            final Uri uri = data.getData();
            final StorageReference childPath = mStorageRef.child("Products").child(myRef.getKey()).child(uri.getLastPathSegment());
            childPath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(addProduct.this,"UPLOAD DONE",Toast.LENGTH_LONG).show();
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


