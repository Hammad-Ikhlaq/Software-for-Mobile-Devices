package com.example.muhammadfakhar.pro;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.CardForm;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<OrderDetail> cartList;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().hide();
        ///
        currUser = UserInstance.currUser;
        cartList = new ArrayList<>();
        cartList = new Database(this).getOrderedItems(); // from sqlite
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("FoodOrders");
        CardForm cardForm = findViewById(R.id.paymentCard);
        Button payBtn = findViewById(R.id.btn_pay);
        TextView amountTV = findViewById(R.id.payment_amount);
        amountTV.setText("Rs. " + totalAmount());
        payBtn.setText(totalAmount());

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartList.isEmpty())
                    Snackbar.make(v, "First Order Something!", Snackbar.LENGTH_LONG).show();
                else
                {
                    alertDialogForAddress();
                }
            }
        });
    }
    private void alertDialogForAddress()
    {
        final EditText eTAddr = new EditText(PaymentMethod.this);
        eTAddr.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        final AlertDialog dialog = new AlertDialog.Builder(PaymentMethod.this)
                .setView(eTAddr)
                .setIcon(R.drawable.cart)
                .setMessage("Enter Your Current Address:")
                .setTitle("Where to Deliver?")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (eTAddr.getText().toString().length() > 0)
                        {
                            //Dismiss once everything is OK.
                            FoodOrders foodOrders = new FoodOrders(currUser.getName(), currUser.getPhone(),
                                    eTAddr.getText().toString(), totalAmount(), true, cartList);
                            // adding to firebase too!
                            databaseReference.child(String.valueOf(System.currentTimeMillis())).setValue(foodOrders);
                            new Database(getBaseContext()).emptyCart();
                            Toast.makeText(PaymentMethod.this, "Order is Placed!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();
                        }

                    }
                });
            }
        });
        dialog.show();
    }
    private String totalAmount()
    {
        int total = 0;
        for (OrderDetail orderDetail:cartList)
        {
            total += (Integer.parseInt(orderDetail.getPrice())) * (Integer.parseInt(orderDetail.getQuantity()));
        }
        return String.valueOf(total);
    }
}
