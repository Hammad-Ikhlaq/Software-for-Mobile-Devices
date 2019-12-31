package com.example.muhammadfakhar.pro;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView cartNameTv, cartPriceTv;
    ImageView cartCountImage, deleteItemImage;

    private ItemClickListener itemClickListener;


    public CartViewHolder(View itemView) {
        super(itemView);
        cartNameTv = itemView.findViewById(R.id.cartItemName);
        cartPriceTv = itemView.findViewById(R.id.cartItemPrice);
        cartCountImage = itemView.findViewById(R.id.cartICount);
        deleteItemImage = itemView.findViewById(R.id.deleteItemBtn);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<OrderDetail> orderDetailList = new ArrayList<>();
    private Context context;

    public CartAdapter(List<OrderDetail> orderDetailList, Context context) {
        this.orderDetailList = orderDetailList;
        this.context = context;
    }

    @NonNull

    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, viewGroup, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder cartViewHolder, int i) {
        TextDrawable textDrawable = TextDrawable.builder().buildRound(
                orderDetailList.get(i).getQuantity(), Color.RED);
        cartViewHolder.cartCountImage.setImageDrawable(textDrawable);

        final int price = Integer.parseInt(orderDetailList.get(i).getPrice()) * Integer.parseInt(orderDetailList.get(i).getQuantity());
        cartViewHolder.cartPriceTv.setText(String.valueOf(price));
        cartViewHolder.cartNameTv.setText(orderDetailList.get(i).getProductName());
        final int pos = i;
        cartViewHolder.deleteItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromCart(pos);
            }
        });

    }

    private void deleteFromCart(int pos) {
        orderDetailList.remove(pos);
        new Database(context).emptyCart(); // clear all sqlite data
        // add again
        for (OrderDetail orderDetail: orderDetailList)
        {
            new Database(context).addToCart(orderDetail);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }
}
