package com.example.fawadbro.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
public class MainActivity extends AppCompatActivity {

    EditText wordBox;
    String word;
    LinearLayout firstlayout;
    MeaningsDbHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordBox= new EditText(this);
        firstlayout= new LinearLayout(this);
        db= new MeaningsDbHelper(this);
        db.populateData();
        createUi();
    }

    public void createUi() {
        setContentView(R.layout.activity_main);
        ScrollView s= new ScrollView(this);
        s.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        firstlayout = new LinearLayout(this);
        firstlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        firstlayout.setOrientation(LinearLayout.VERTICAL);

        TextView dictText = new TextView(this);
        dictText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 100));
        dictText.setText("Dictionary");

        firstlayout.addView(dictText);

        TextView wordText = new TextView(this);
        wordText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        wordText.setText("Word");

        firstlayout.addView(wordText);

        wordBox = new EditText(this);
        wordBox.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        firstlayout.addView(wordBox);

        Button Lookup = new Button(this);
        Lookup.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        Lookup.setText("Lookup");
        Lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showMeanings();
            }
        });

        firstlayout.addView(Lookup);

        TextView meaningText = new TextView(this);
        meaningText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        meaningText.setText("Meanings");

        firstlayout.addView(meaningText);
        s.addView(firstlayout);
        setContentView(s);
    }
   public void showMeanings()
    {
        word =String.valueOf(wordBox.getText());
        Intent intent = new Intent(this,HistoryService.class);
        intent.putExtra("hword",word);
        startService(intent);
        createUi();
        Cursor res = db.getAllData(word);

        if(res.getCount()==0)
        {
            return;
        }
        while(res.moveToNext())
        {
            TextView m=new TextView(this);
            m.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            m.setText(res.getString(0));
            firstlayout.addView(m);
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        try{
            savedInstanceState.putString("word",word);
        }
        catch(Exception ex){ }
    }
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        try{
            word = (String) savedInstanceState.getString("word");
            Cursor res = db.getAllData(word);
            if(res.getCount()==0)
            {
                return;
            }
            while(res.moveToNext())
            {
                TextView m=new TextView(this);
                m.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                m.setText(res.getString(0));
                firstlayout.addView(m);
            }
        }
        catch(Exception ex){ }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.m,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem Item)
    {
        if(Item.getItemId()==R.id.l)
        {
            Intent intent=new Intent(this, History.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}

