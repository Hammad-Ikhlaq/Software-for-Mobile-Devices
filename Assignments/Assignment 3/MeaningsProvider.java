package com.example.fawadbro.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MeaningsProvider extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI("com.example.fawadbro.myapplication.MeaningsProvider","meanings",1);
    }
    private MeaningsDbHelper dbHelper;
    private SQLiteDatabase db;

    public boolean onCreate()
    {
        dbHelper = new MeaningsDbHelper(getContext());
        db = dbHelper.getReadableDatabase();
        return true;
    }
    public String getType(Uri uri){
        return null;
    }
    public Uri insert(Uri uri,ContentValues values){
        return null;
    }
    public int update(Uri uri, ContentValues values, String selection, String[] args){
        return 0;
    }
    public int delete(Uri uri,String selection,String[] args){
        return 0;
    }

    public Cursor query(Uri uri, String [] projection,
                        String selection, String[] selectionArgs, String sortOrder){

        if(matcher.match(uri) == 1){
            return db.query("Meanings",projection,selection,selectionArgs,null,null,sortOrder);
      }
        String[] cols = {"_ID"};
        return new MatrixCursor(cols);
    }

}
