package com.example.muhammadfakhar.pro;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class SignUpActivity extends AppCompatActivity {

    private EditText phoneET, passET, nameET, emailET;
    private Button signUp;
    private ProgressBar progressBar;
    String pname, pass, phoneno, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressBar = findViewById(R.id.progressBar);
        phoneET = findViewById(R.id.etPhoneNo);
        nameET = findViewById(R.id.etName);
        passET = findViewById(R.id.etPassword);
        signUp = (findViewById(R.id.signup));
        emailET = findViewById(R.id.emailET);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference userTable = firebaseDatabase.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pname = nameET.getText().toString();
                pass = passET.getText().toString();
                email = emailET.getText().toString();
                phoneno = phoneET.getText().toString();
                if (!isEmailValid(email)){
                    Toast.makeText(SignUpActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }

                if (pass.isEmpty() || phoneno.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Empty Fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkConn() == true) {
                    progressBar.setVisibility(View.VISIBLE);
                    userTable.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (dataSnapshot.child(phoneno).exists())
                            {
                                Toast.makeText(SignUpActivity.this, "Phone Already Exists!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                User nUser = new User(pname, pass, phoneno, email);
                                userTable.child(phoneno).setValue(nUser);
                                UserInstance.currUser = nUser;
                                Intent intent = new Intent(SignUpActivity.this, Home.class);
                                startActivity(intent);
                                finish(); // activity killed from stack
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean checkConn()
    {
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        // source android dev
        return isConnected;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
