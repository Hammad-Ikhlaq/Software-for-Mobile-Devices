package com.example.fawadbro.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

public class MeaningsDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Meanings.db";
    Context context;

    public MeaningsDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context =context;
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE Meanings (Word TEXT , " + "Meaning TEXT,PRIMARY KEY (Word, Meaning)) ";
        db.execSQL(sql);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Meanings");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
    public void populateData()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Word","happy");
        values.put("Meaning","feeling or showing pleasure or contentment or fortunate or convenient");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","cheerfull");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","joyful");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","glowing");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","delighted");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","merry");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","gleeful");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","happy");
        values.put("Meaning","carefree");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","sad");
        values.put("Meaning","feeling or showing sorrow; unhappy");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","sad");
        values.put("Meaning","despairing");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();
        values.put("Word","sad");
        values.put("Meaning","heart broken");
        db.insertWithOnConflict("Meanings",null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }
    public Cursor getAllData(String word)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Meaning FROM Meanings where Word ='"+word+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void EnterInHistory(String word) {

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
               FileOutputStream fos = new FileOutputStream(file,true);
                word+="\n";
                fos.write(word.getBytes());
                fos.close();

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(context, "sd card not found", Toast.LENGTH_SHORT).show();
    }
}
