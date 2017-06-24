package com.a46inch.a46inch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @NonNull FirebaseAuth firebaseAuth;
    ListView CatagoryList;
    String Uid;
    public String[] CATAGORY={"PROTEINS","PRE/POST WORKOUT","OTHERS"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Uid =user.getUid();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //initialize toolbar
        setSupportActionBar(toolbar);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview,CATAGORY);
        CatagoryList = (ListView)  findViewById(R.id.CatagoryList);
        CatagoryList.setAdapter(adapter);
        CatagoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent homeIntent = new Intent(Home.this, ProteinsActivity.class);
                    startActivity(homeIntent);
                }
                else if(position == 1){
                    Intent homeIntent = new Intent(Home.this, PrepostActivity.class);
                    startActivity(homeIntent);
                }
                else if(position == 2){
                    Intent homeIntent = new Intent(Home.this, OthersActivity.class);
                    startActivity(homeIntent);
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //initialize DrawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close); //initialize ActionBarDrawerToggle
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); //initialize NavigationView
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	//changes to be done in the given below methord

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {


        } else if (id == R.id.nav_myCart) {
            Intent i2 = new Intent(Home.this,wishlistActivity.class);
            startActivity(i2);


        } else if (id == R.id.nav_MyOrders) {

        } else if (id == R.id.nav_MyAccount) {
            Intent i2 = new Intent(Home.this,MyAccountActivity.class);
            startActivity(i2);

        } else if (id == R.id.nav_Faq) {
            Intent i2 = new Intent(Home.this,FAQActivity.class);
            startActivity(i2);

        } else if (id == R.id.nav_Logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, SignActivity.class)); //Go back to home page
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
