package com.example.fawadbro.myapplication;

import java.util.ArrayList;
import java.util.UUID;

public class Contacts {
    private String id;
    private String name;
    private ArrayList<String> email;
    private ArrayList<String> phone;

    public Contacts(){
        init();
    }
    public Contacts(String name,ArrayList<String>email){
        init();
        this.name = name;
        this.email = email;
    }
    private void init()
    {
        this.id = UUID.randomUUID().toString();
    }
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(ArrayList<String>email)
    {
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone.add(phone);
    }
}
