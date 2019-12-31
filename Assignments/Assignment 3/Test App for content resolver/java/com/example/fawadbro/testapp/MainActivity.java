package com.example.fawadbro.testapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        function();

    }
    public void function()
    {

        String[] colNames = {"Word","Meaning"};

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.fawadbro.myapplication.MeaningsProvider/meanings"),colNames,null,null,null);

        if (cursor.getCount() > 0){

            String result = "";
            ScrollView s= new ScrollView(this);
            s.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            LinearLayout firstlayout = new LinearLayout(this);
            firstlayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            firstlayout.setOrientation(LinearLayout.VERTICAL);
            while (cursor.moveToNext()){
                result = cursor.getString(cursor.getColumnIndex("Word")) +":  "+cursor.getString(cursor.getColumnIndex("Meaning")) + "\n";
                TextView dictText = new TextView(this);
                dictText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));
                dictText.setText(result);
                firstlayout.addView(dictText);
            }
            s.addView(firstlayout);
            setContentView(s);
        }
    }
}
