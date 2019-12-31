package com.example.muhammadfakhar.pro;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendMailTask extends AsyncTask {

    private Activity sendMailActivity;

    public SendMailTask(Activity activity) {
        sendMailActivity = activity;

    }

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"fakhar571@gmail.com"};
        String[] CC = {UserInstance.currUser.getEmail()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "---Feedback---");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Feedback goes here...");

        try {
            sendMailActivity.startActivity(Intent.createChooser(emailIntent, "Send Mail..."));
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(sendMailActivity.getBaseContext(),
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onPreExecute() {
    }

    @Override
    protected Object doInBackground(Object... args) {
        sendEmail();
        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {

    }

    @Override
    public void onPostExecute(Object result) {
    }

}