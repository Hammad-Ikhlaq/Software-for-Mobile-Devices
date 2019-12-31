package com.example.fawadbro.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            File Root = Environment.getExternalStorageDirectory();

            File file = new File(Root,"History.txt");

            try {
                if(!file.exists())
                {
                    file.createNewFile();
                }

                ScrollView s= new ScrollView(this);
                s.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                LinearLayout firstlayout = new LinearLayout(this);
                firstlayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                firstlayout.setOrientation(LinearLayout.VERTICAL);


                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while((line = br.readLine())!=null)
                {
                    TextView dictText = new TextView(this);
                    dictText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100));
                    dictText.setText(line);

                    firstlayout.addView(dictText);
                }
                s.addView(firstlayout);
                setContentView(s);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
