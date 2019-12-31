
package com.example.fawadbro.myapplication;

import java.util.ArrayList;
import android.app.Activity;
import android.icu.text.MeasureFormat;
import android.os.Bundle;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.*;
import android.widget.RelativeLayout.LayoutParams;


public class MainActivity extends Activity
{
    ArrayList<Contacts> contacts;
    Contacts currentContact=new Contacts();
    EditText name;
    ArrayList<EditText> email= new ArrayList<EditText>();
    int lastid,lastidP;
    Button button;
    EditText textid;
    TextView textp;
    ArrayList<EditText> phone= new ArrayList<EditText>();
    RelativeLayout layout;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= ((EditText) findViewById(R.id.name2));

        Button cancelb=new Button(this);
        cancelb=(Button) findViewById(R.id.buttoncancel);
        cancelb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancelContact();
            }
        });


        email.add((EditText) findViewById(R.id.name4));
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout = (RelativeLayout) findViewById(R.id.relative);
        lastid=email.get(0).getId();

        Button ButtonE = new Button(this);
        ButtonE.setLayoutParams(params);
        ButtonE.setText("Add Email");
        ButtonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AddEmail();
            }
        });
        params.addRule(RelativeLayout.BELOW, email.get(0).getId());
        params.addRule(RelativeLayout.ALIGN_RIGHT, email.get(0).getId());
        layout.addView(ButtonE);
        button=ButtonE;

        LayoutParams params2 = new LayoutParams(800, 800);
        TextView phoneT = new TextView(this);
        phoneT.setText("Phone");
        params2.addRule(RelativeLayout.BELOW, lastid);
        params2.addRule(RelativeLayout.ALIGN_LEFT, lastid);
        params2.topMargin=140;
        phoneT.setLayoutParams(params2);
        phoneT.setTextSize(20);
        layout.addView(phoneT);
        textp=phoneT;
        setContentView(layout);

        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(740, RelativeLayout.LayoutParams.WRAP_CONTENT);
        EditText textArea1 = new EditText(this);
        params3.addRule(RelativeLayout.BELOW, lastid);
        params3.topMargin=200;
        textArea1.setLayoutParams(params3);
        layout.addView(textArea1);
        phone.add(textArea1);
        textid = textArea1;
        lastidP=phone.get(0).getId();

        Button saveb=new Button(this);
        saveb=(Button) findViewById(R.id.buttonsave);
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveContact();
            }
        });

        String text = "OnCreate";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
        toast.show();
    }

    private void saveContact() {
        String Name = new String();
        Name =name.getText().toString();
        ArrayList<String> Email=new ArrayList<String>();
        for(int i=0;i<email.size();i++) {
            Email.add(email.get(i).getText().toString());
        }
       // String Phone = phone.toString();
       if (currentContact == null) {
            currentContact = new Contacts(Name, Email);
            contacts.add(currentContact);
        }
        currentContact.setName(Name);
        currentContact.setEmail(Email);
       // currentContact.setPhone(Phone);
        cancelContact();
        String text = "Contact saved successfully";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }
    private void cancelContact(){
        name.setText("");

        for(int i = 0;i<email.size();i++)
        {
            email.get(0).setText("");
        }
        email.clear();
        phone.clear();
        currentContact = null;

        String text = "Contact canceled successfully";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }
    private void AddEmail() {

        LayoutParams params = new LayoutParams(740, LayoutParams.WRAP_CONTENT);
        LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams params3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        EditText textArea = new EditText(this);
        params.addRule(RelativeLayout.BELOW, lastid);
        textArea.setLayoutParams(params);

        lastid=lastid+1;
        textArea.setId(lastid);
        layout.addView(textArea);

        params2.addRule(RelativeLayout.BELOW, lastid);
        params2.addRule(RelativeLayout.ALIGN_RIGHT, lastid);
        button.setLayoutParams(params2);

        setContentView(layout);

        String text = "Add Email Function"+email.size();
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
        toast.show();

    }

    private void AddPhone() {
    }

    private void RemoveEmail() {
    }

    private void RemovePhone() {
    }



}
