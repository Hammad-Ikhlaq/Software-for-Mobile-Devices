package com.example.fawadbro.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends Activity {
    ArrayList<Contacts> contacts;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(contacts == null){
            contacts = new ArrayList<Contacts>();
        }
        Intent intent = getIntent();
        contacts = (ArrayList<Contacts>) intent.getSerializableExtra("list");


         createView();
    }

    private void createView(){
        ListView view = new ListView(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        String [] array = new String[contacts.size()];
        for(int i=0; i < contacts.size(); i++){
            array[i] = contacts.get(i).name;
        }
        ContactListAdapter adapter = new ContactListAdapter(this,contacts);

        view.setAdapter(adapter);
        setContentView(view);

        String text = "In CreateView Function";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }
}

