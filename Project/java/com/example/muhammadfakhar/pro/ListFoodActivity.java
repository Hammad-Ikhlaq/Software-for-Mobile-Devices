package com.example.muhammadfakhar.pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ListFoodActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference foodRef;
    private TextView nameTv;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rVLayoutManager;
    private String cuisineId;
    FirebaseRecyclerAdapter<FoodItem, FoodViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        ////
        getSupportActionBar().setTitle("Foods");
        firebaseDatabase = FirebaseDatabase.getInstance();
        foodRef = firebaseDatabase.getReference("Foods");
        recyclerView = findViewById(R.id.rec_food_list);
        recyclerView.setHasFixedSize(true);
        rVLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rVLayoutManager);

        Intent intent = getIntent();
        cuisineId = intent.getStringExtra("Cuisine Id");
        
        if (cuisineId != null)
        {
            listFood(cuisineId);
        }
    }

    private void listFood(String cuisineId) {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FoodItem, FoodViewHolder>(FoodItem.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodRef.orderByChild("MenuId").equalTo(cuisineId))// select * from food where cuisine = menuId...
        {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, FoodItem model, int position) {
                viewHolder.foodName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final FoodItem curr = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent = new Intent(ListFoodActivity.this, FoodDetails.class);
                        intent.putExtra("Food Id", firebaseRecyclerAdapter.getRef(position).getKey()); // view click is actually food Id
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
