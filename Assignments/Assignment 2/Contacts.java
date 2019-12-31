package com.example.fawadbro.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
public class Contacts implements Serializable {
    public String id;
    public String name;
    public String lname;
    public String title;
    public ArrayList<String> email =new ArrayList<String>();
    public ArrayList<String> phone =new ArrayList<String>();

    public Contacts(){
        init();
    }
    public Contacts(String name,String lname,String title,ArrayList<String>email1,ArrayList<String>phone1){
        init();
        this.name = name;
        this.lname = lname;
        this.title = title;
        this.email = email1;
        this.phone = phone1;
    }
    public void init()
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
