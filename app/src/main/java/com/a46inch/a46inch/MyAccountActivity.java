package com.a46inch.a46inch;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a46inch.a46inch.Classes.Userinfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MyAccountActivity extends AppCompatActivity {
    private TextView name,dob,addr,phno,email;
    private ImageButton Profilepic;
    private Button edit;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private Query query;
    private String url;
    private StorageReference mStorageref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        name = (TextView) findViewById(R.id.name);
        dob = (TextView) findViewById(R.id.dob);
        phno = (TextView) findViewById(R.id.phoneno);
        addr = (TextView) findViewById(R.id.address);
        email = (TextView) findViewById(R.id.email);
        Profilepic = (ImageButton) findViewById(R.id.profilePic);
        edit = (Button) findViewById(R.id.editButton);
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        myRef=mFirebaseDatabase.getInstance().getReference().child("UserInfo").child(user.getUid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Userinfo user1 = dataSnapshot.getValue(Userinfo.class);
                display(user1.getAname(),user1.getAdob(),user1.getAaddress(),user1.getAphone_num(),user1.getAemail(),user1.getApicuri());
                url = user1.getApicuri();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        Profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview(url);
            }
        });




    }

    private void preview(String a) {
        Dialog d1 = new Dialog(this);
        d1.setContentView(R.layout.previewimage);
        final ImageView Dpic = (ImageView) d1.findViewById(R.id.dpic);
        Picasso.with(this).load(a).into(Dpic);
        d1.show();
    }

    public void display(String a, String b, String c, String d, String e,  String ur){
        name.setText("Name: "+a);
        dob.setText("DATE OF BIRTH: "+b);
        addr.setText("ADDRESS: "+c);
        phno.setText("PH.NO: "+d);
        email.setText("EMAIL: "+e);
        Picasso.with(this).load(ur).into(Profilepic);



    }

}
