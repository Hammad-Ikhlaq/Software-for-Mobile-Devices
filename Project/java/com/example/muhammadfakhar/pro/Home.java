package com.example.muhammadfakhar.pro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference cuisinesRef;
    private TextView nameTv, emailTV;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rVLayoutManager;
    FirebaseRecyclerAdapter<Cuisines, MenuViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        // Paper

        Paper.init(this);

        // firebase

        firebaseDatabase = FirebaseDatabase.getInstance();
        cuisinesRef = firebaseDatabase.getReference("Cuisines");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(Home.this, CartDetails.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ////
        View view = navigationView.getHeaderView(0);
        nameTv = view.findViewById(R.id.nameTv);
        emailTV = view.findViewById(R.id.emailTV);
        recyclerView = findViewById(R.id.recMenu);
        recyclerView.setHasFixedSize(true);
        rVLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rVLayoutManager);
        nameTv.setText(UserInstance.currUser.getName());
        emailTV.setText(UserInstance.currUser.getEmail());
        bindMenu();

    }

    private void bindMenu() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cuisines, MenuViewHolder>(Cuisines.class, R.layout.menu_item, MenuViewHolder.class, cuisinesRef) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Cuisines model, int position) {
                viewHolder.cuisineName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Cuisines cuisines = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(Home.this, ListFoodActivity.class);
                        intent.putExtra("Cuisine Id", firebaseRecyclerAdapter.getRef(position).getKey()); // cuisine id is the key
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true); // to not move to login page again, it just changes the stack
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu)
        {

        }
        else if (id == R.id.nav_cart)
        {
            Intent intent = new Intent(Home.this, CartDetails.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_orders)
        {
            Intent intent = new Intent(Home.this, OrderDeliveryStatus.class);
            startActivity(intent);
        }
        else if (id == R.id.feedBackItem)
        {
            new SendMailTask(this).execute();
        }
        else if (id == R.id.nav_logout)
        {
            Paper.book().destroy(); // destroy the remembered
            Intent intent = new Intent(Home.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
