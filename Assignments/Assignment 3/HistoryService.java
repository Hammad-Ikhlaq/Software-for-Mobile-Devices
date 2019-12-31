package com.example.fawadbro.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class HistoryService extends Service {

    MeaningsDbHelper db;
    private final IBinder binder = new LocalBinder();
    public void onCreate() {

        db= new MeaningsDbHelper(this);
    }
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String word = (String) intent.getStringExtra("hword");
        db.EnterInHistory(word);
        return START_NOT_STICKY;
    }
    public IBinder onBind(Intent intent){
        return binder;
    }
    public class LocalBinder extends Binder
    {
        public HistoryService getService(){
            return HistoryService.this;
        }
    }
}
