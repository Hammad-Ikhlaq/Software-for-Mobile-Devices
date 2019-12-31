package com.example.muhammadfakhar.pro;

import java.util.List;

public class FoodOrders {
    private String name, phone, address, totalAmount, deliveryStatus;
    private boolean amountPStatus;
    List<OrderDetail> ordersList;

    public FoodOrders() {
    }

    public FoodOrders(String name, String phone, String address, String totalAmount, boolean amountPaid, List<OrderDetail> ordersList) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.totalAmount = totalAmount;
        this.ordersList = ordersList;
        this.amountPStatus = amountPaid;
        this.deliveryStatus = "Processing...";
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderDetail> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrderDetail> ordersList) {
        this.ordersList = ordersList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
