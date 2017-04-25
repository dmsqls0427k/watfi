package com.example.danae.wat2340.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Users;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;

public class ViewProfile extends AppCompatActivity {
    private Button editChange;
    private Button editCancel;

    private EditText editName, email, password, conPass;
    private Users user;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        editCancel = (Button) findViewById(R.id.editCancel);
        editChange = (Button) findViewById(R.id.editChange);
        editName = (EditText) findViewById(R.id._name);
        email = (EditText) findViewById(R.id._email);
        password = (EditText) findViewById(R.id._password);
        conPass = (EditText) findViewById(R.id._confPassword);

        editName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        editChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //changeProfile();
                databaseReference.child("user").orderByChild("email").equalTo(currentUser.getEmail())
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        String key = null;

                        for (DataSnapshot child : children) {
                            key = child.getKey();
                        }

                        if (conPass.getText().toString().equals(password.getText().toString())) {
                            if (!editName.getText().toString().equals(currentUser.getName())) {
                                databaseReference.child("user").child(key).child("name").setValue(editName.getText().toString());
                            }
                            if (!email.getText().toString().equals(currentUser.getEmail())) {
                                Toast.makeText(ViewProfile.this,"Email cannot be changed",Toast.LENGTH_LONG).show();
                            }
                            if (!password.getText().toString().equals(currentUser.getPassword())) {
                                databaseReference.child("user").child(key).child("password").setValue(password.getText().toString());
                            }
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Your password and confirmation does not match.";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        editCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
