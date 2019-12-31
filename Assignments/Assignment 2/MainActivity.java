package com.example.fawadbro.myapplication;
import java.io.Serializable;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
public class MainActivity extends Activity {

    public EditText name;
    public EditText lname;
    public EditText title;
    public ArrayList<Contacts> contacts = new ArrayList<Contacts>();
    public ArrayList<EditText> emails;
    public ArrayList<EditText> phones;
    public Contacts currentContact;
    public LinearLayout EmailLayout;
    public LinearLayout PhoneLayout;
    public ArrayList<LinearLayout> EmailHLayouts = new ArrayList<LinearLayout>();
    public ArrayList<LinearLayout> PhoneHLayouts = new ArrayList<LinearLayout>();
    public ArrayList<Button> EmailButtons = new ArrayList<Button>();
    public ArrayList<Button> PhoneButtons = new ArrayList<Button>();
    public int EmailCounter =1;
    public int PhoneCounter =101;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = new EditText(this);
        lname = new EditText(this);
        title = new EditText(this);
        emails = new ArrayList<EditText>();
        phones = new ArrayList<EditText>();
        currentContact = new Contacts();

        createUi();
    }

    public void createUi() {

        setContentView(R.layout.activity_main);

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        firstlayout.setOrientation(LinearLayout.VERTICAL);
        firstlayout.setGravity(Gravity.TOP);


        firstlayout.addView(createButtons());
        firstlayout.addView(createListButton());
        firstlayout.addView(CreateFirstNameText());
        firstlayout.addView(CreateFirstNameTextBox());
        firstlayout.addView(CreateSecondNameText());
        firstlayout.addView(CreateSecondNameTextBox());
        firstlayout.addView(TitleNameText());
        firstlayout.addView(TitleTextBox());
        firstlayout.addView(CreateEmailText());
        firstlayout.addView(CreateEmailTextBox());
        firstlayout.addView(CreateAddEmailButton());
        firstlayout.addView(CreatePhoneText());
        firstlayout.addView(CreatePhoneTextBox());
        firstlayout.addView(CreateAddPhoneButton());

        setContentView(firstlayout);
    }

    public ViewGroup createButtons() {

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);
        firstlayout.setGravity(Gravity.TOP);

        TextView contactText = new TextView(this);
        contactText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        contactText.setText("AddContact");
        contactText.setGravity(Gravity.TOP);

        firstlayout.addView(contactText);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("Save");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                saveContact();
            }
        });
        firstlayout.addView(saveButton);
        Button cancelButton = new Button(this);
        cancelButton.setLayoutParams(params);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancelContact();
            }
        });
        firstlayout.addView(cancelButton);
        return firstlayout;
    }
    public ViewGroup createListButton()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        Button listB = new Button(this);
        listB.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        listB.setText("List");
        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                AddActivity();
            }
        });

        firstlayout.addView(listB);
        return firstlayout;
    }
    public ViewGroup CreateFirstNameText(){

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nameText.setText("First Name");

        firstlayout.addView(nameText);
        return firstlayout;
    }

    public ViewGroup CreateFirstNameTextBox()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        name = new EditText(this);
        name.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));
        firstlayout.addView(name);
        return firstlayout;
    }
    public ViewGroup CreateSecondNameText(){

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nameText.setText("Second Name");

        firstlayout.addView(nameText);
        return firstlayout;
    }

    public ViewGroup CreateSecondNameTextBox()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        lname = new EditText(this);
        lname.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));
        firstlayout.addView(lname);
        return firstlayout;
    }
    public ViewGroup TitleNameText(){

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nameText.setText("Title");

        firstlayout.addView(nameText);
        return firstlayout;
    }

    public ViewGroup TitleTextBox()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        title = new EditText(this);
        title.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));
        firstlayout.addView(title);
        return firstlayout;
    }
    public ViewGroup CreateEmailText(){

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nameText.setText("Email");

        firstlayout.addView(nameText);
        return firstlayout;
    }

    public ViewGroup CreateEmailTextBox()
    {
        EmailLayout = new LinearLayout(this);
        EmailLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        EmailLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText nameBox = new EditText(this);
        nameBox.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));
        firstlayout.addView(nameBox);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("X");
        saveButton.setId(EmailCounter);
        EmailCounter++;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for(int i = 0; i<EmailHLayouts.size();i++)
                {
                    if(arg0.getId()==(EmailButtons.get(i)).getId())
                    {
                        EmailHLayouts.get(i).removeAllViews();
                        EmailHLayouts.remove(i);
                        EmailButtons.remove(i);
                        EmailCounter--;
                    }
                }
            }
        });
        EmailButtons.add(saveButton);
        firstlayout.addView(saveButton);
        EmailLayout.addView(firstlayout);
        EmailHLayouts.add(firstlayout);
        emails.add(nameBox);
        return EmailLayout;
    }

    public ViewGroup CreateAddEmailButton()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("Add Email");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AddEmail();
            }
        });
        firstlayout.addView(saveButton);

        return firstlayout;
    }

    public ViewGroup CreatePhoneText(){

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView nameText = new TextView(this);
        nameText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nameText.setText("Phone");

        firstlayout.addView(nameText);
        return firstlayout;
    }

    public ViewGroup CreatePhoneTextBox()
    {
        PhoneLayout = new LinearLayout(this);
        PhoneLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        PhoneLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText nameBox = new EditText(this);
        nameBox.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));

        firstlayout.addView(nameBox);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("X");
        saveButton.setId(PhoneCounter);
        PhoneCounter++;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for(int i = 0; i<PhoneHLayouts.size();i++)
                {
                    if(arg0.getId()==(PhoneButtons.get(i)).getId())
                    {
                        PhoneHLayouts.get(i).removeAllViews();
                        PhoneHLayouts.remove(i);
                        PhoneButtons.remove(i);
                        PhoneCounter--;
                    }
                }
            }
        });
        PhoneButtons.add(saveButton);
        firstlayout.addView(saveButton);
        PhoneLayout.addView(firstlayout);
        PhoneHLayouts.add(firstlayout);
        phones.add(nameBox);
        return PhoneLayout;
    }

    public ViewGroup CreateAddPhoneButton()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("Add Phone");
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AddPhone();
            }
        });
        firstlayout.addView(saveButton);

        return firstlayout;
    }

    public void AddEmail()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText nameBox = new EditText(this);
        nameBox.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));
        firstlayout.addView(nameBox);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("X");
        saveButton.setId(EmailCounter);
        EmailCounter++;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for(int i = 0; i<EmailHLayouts.size();i++)
                {
                    if(arg0.getId()==(EmailButtons.get(i)).getId())
                    {
                        EmailHLayouts.get(i).removeAllViews();
                        EmailHLayouts.remove(i);
                        EmailButtons.remove(i);
                        EmailCounter--;
                    }
                }
            }
        });
        EmailButtons.add(saveButton);
        firstlayout.addView(saveButton);
        EmailHLayouts.add(firstlayout);
        EmailLayout.addView(firstlayout);
    }

    public void AddPhone()
    {
        LinearLayout firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        firstlayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText nameBox = new EditText(this);
        nameBox.setLayoutParams(new LayoutParams(900, LayoutParams.WRAP_CONTENT));

        firstlayout.addView(nameBox);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button saveButton = new Button(this);
        saveButton.setLayoutParams(params);
        saveButton.setText("X");
        saveButton.setId(PhoneCounter);
        PhoneCounter++;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for(int i = 0; i<PhoneHLayouts.size();i++)
                {
                    if(arg0.getId()==(PhoneButtons.get(i)).getId())
                    {
                        PhoneHLayouts.get(i).removeAllViews();
                        PhoneHLayouts.remove(i);
                        PhoneButtons.remove(i);
                        PhoneCounter--;
                    }
                }
            }
        });
        PhoneButtons.add(saveButton);
        firstlayout.addView(saveButton);
        PhoneHLayouts.add(firstlayout);
        PhoneLayout.addView(firstlayout);
    }

    public void saveContact() {
        String Name = new String();
        Name = name.getText().toString();
        String Lname = new String();
        Lname = lname.getText().toString();
        String Title = new String();
        Title = title.getText().toString();
        ArrayList<String> Email = new ArrayList<String>();
        for (int i = 0; i < emails.size(); i++) {
            Email.add(emails.get(i).getText().toString());
        }
        ArrayList<String> Phone = new ArrayList<String>();
        for (int i = 0; i < phones.size(); i++) {
            Phone.add(phones.get(i).getText().toString());
        }


        Contacts currentContact = new Contacts(Name,Lname,Title, Email,Phone);

        contacts.add(currentContact);
        createUi();
    }

    public void cancelContact(){

        emails.clear();
        phones.clear();
        EmailHLayouts.clear();
        PhoneHLayouts.clear();
        EmailButtons.clear();
        PhoneButtons.clear();
        currentContact = null;
        createUi();


        String text = "Contact canceled successfully";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void AddActivity()
    {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("list",(Serializable)contacts);

            startActivity(intent);

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        try{
            savedInstanceState.putSerializable("noteslist",contacts);
        }
        catch(Exception ex){ }
    }
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        try{
            contacts = (ArrayList<Contacts>) savedInstanceState.getSerializable("noteslist");
        }
        catch(Exception ex){ }
    }
}

