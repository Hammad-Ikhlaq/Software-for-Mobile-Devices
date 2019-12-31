package com.example.muhammadfakhar.pro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OrderViewHolder extends RecyclerView.ViewHolder{

    private TextView orderIdTv, orderStatusTv, userAddrTv, userPhoneTv;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        orderIdTv = itemView.findViewById(R.id.orderId);
        orderStatusTv = itemView.findViewById(R.id.orderStatus);
        userAddrTv = itemView.findViewById(R.id.userAddrOrder);
        userPhoneTv = itemView.findViewById(R.id.userPhoneOrder);

    }

    public TextView getOrderIdTv() {
        return orderIdTv;
    }

    public void setOrderIdTv(TextView orderIdTv) {
        this.orderIdTv = orderIdTv;
    }

    public TextView getOrderStatusTv() {
        return orderStatusTv;
    }

    public void setOrderStatusTv(TextView orderStatusTv) {
        this.orderStatusTv = orderStatusTv;
    }

    public TextView getUserAddrTv() {
        return userAddrTv;
    }

    public void setUserAddrTv(TextView userAddrTv) {
        this.userAddrTv = userAddrTv;
    }

    public TextView getUserPhoneTv() {
        return userPhoneTv;
    }

    public void setUserPhoneTv(TextView userPhoneTv) {
        this.userPhoneTv = userPhoneTv;
    }

}
