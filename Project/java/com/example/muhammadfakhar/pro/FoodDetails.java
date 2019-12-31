package com.example.muhammadfakhar.pro;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetails extends AppCompatActivity {

    private TextView foodName, foodDesc, foodPrice;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton cartBtn;
    private ElegantNumberButton elegantNumberButton;
    private String foodId = null;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference foodRef;
    private FoodItem currFoodItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        getSupportActionBar().hide();
        ///
        firebaseDatabase = FirebaseDatabase.getInstance();
        foodRef = firebaseDatabase.getReference("Foods");

        elegantNumberButton = findViewById(R.id.elegantBtn);
        cartBtn = findViewById(R.id.cartBtn);
        ///
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new Database(getBaseContext()).addToCart(new OrderDetail(foodId,
                         currFoodItem.getName(), elegantNumberButton.getNumber(),
                         currFoodItem.getPrice(), currFoodItem.getDiscount()));
                Toast.makeText(FoodDetails.this, "Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
        foodName = findViewById(R.id.foodName);
        foodDesc = findViewById(R.id.foodDesc);
        imageView = findViewById(R.id.foodImage);
        foodPrice = findViewById(R.id.foodPrice);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsingAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandingAppbar);

        if (getIntent() != null)
        {
            foodId = getIntent().getStringExtra("Food Id");
        }
        if (foodId != null)
        {
            getFoodDetail(foodId);
        }
    }

    private void getFoodDetail(String foodId) {
        foodRef.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currFoodItem = dataSnapshot.getValue(FoodItem.class);

                Picasso.with(getBaseContext()).load(currFoodItem.getImage()).into(imageView);
                collapsingToolbarLayout.setTitle(currFoodItem.getName());
                foodPrice.setText(currFoodItem.getPrice());
                foodDesc.setText(currFoodItem.getDescription());
                foodName.setText(currFoodItem.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
