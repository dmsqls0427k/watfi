package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.Model.Users;
import com.example.danae.wat2340.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText emailText, passText;
    Button cancelBtn, registerBtn, loginBtn;
    String email, password;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public static Users currentUser;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = (EditText) findViewById(R.id.emailText);
        passText = (EditText) findViewById(R.id.passText);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passText.getText().toString();

                if (checkValidId(email, password)) {
                    if (currentUser.getUserType() == Standing.USER) {
                        startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                    } else if (currentUser.getUserType() == Standing.WORKER) {
                        startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                    } else if (currentUser.getUserType() == Standing.MANAGER) {
                        startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                    }
                } else {
                    counter--;
                    if (counter == 0) {
                        Toast.makeText(getApplicationContext(), "Account is locked", Toast.LENGTH_LONG).show();
                        loginBtn.setEnabled(false);
                    }
                }
            }
        });
    }

    public boolean checkValidId(String email, String password) {
        if (email == null || password == null) {
            Toast.makeText(this, "One or more field is empty", Toast.LENGTH_LONG).show();
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    setCurrentUser();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Attempt Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        return currentUser != null;
    }

    public void setCurrentUser() {
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    Users childValue = child.getValue(Users.class);

                    if (childValue.getEmail().equals(email)) {
                        currentUser = childValue;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
