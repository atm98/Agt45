package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a46inch.a46inch.Classes.Userinfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyAccountActivity extends AppCompatActivity {
    private TextView name,dob,addr,phno,email;
    private ImageView Profilepic;
    private Button edit;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private Query query;
    String uemail;
    private String uname,udob,uadd,uph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        name = (TextView) findViewById(R.id.name);
        dob = (TextView) findViewById(R.id.dob);
        phno = (TextView) findViewById(R.id.phoneno);
        addr = (TextView) findViewById(R.id.address);
        email = (TextView) findViewById(R.id.email);
        Profilepic = (ImageView) findViewById(R.id.profilePic);
        edit = (Button) findViewById(R.id.editButton);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uemail= user.getEmail().toString().trim();
        myRef=mFirebaseDatabase.getInstance().getReference().child("UserInfo");
        query = myRef.orderByChild("aemail").equalTo(uemail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Userinfo user = dataSnapshot.getValue(Userinfo.class);
                uname = user.aname;
                udob = user.adob;
                uadd = user.aaddress;
                uph =  user.aphone_num;
                display(uname,udob,uadd,uph,uemail);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public void display(String a,String b , String c, String d, String e){
        name.setText("Name: "+a);
        dob.setText("DATE OF BIRTH: "+b);
        addr.setText("ADDRESS: "+c);
        phno.setText("PH.NO: "+d);
        email.setText("EMAIL: "+e);

    }
}
