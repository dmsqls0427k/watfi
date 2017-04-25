package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;
import static com.example.danae.wat2340.Controller.viewSourceReport.sourceReport;

public class EditSourceReport extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    SourceReport selected;

    EditText latitude, longitude;
    TextView numberText, nameText, dateText;
    Spinner conditionSpinner, typeSpinner;
    Button submitBtn, cancelBtn;

    String key;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soure_report);

        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        numberText = (TextView) findViewById(R.id.countText);
        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        nameText.setText(sourceReport.getName());
        dateText.setText(date);

        numberText.setText("" + sourceReport.getId());

        longitude.setText(sourceReport.getLongitude());
        latitude.setText(sourceReport.getLatitude());



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getUserType() == Standing.USER) {
                    startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                } else if (currentUser.getUserType() == Standing.WORKER) {
                    startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                } else if (currentUser.getUserType() == Standing.MANAGER) {
                    startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseReference.child("source").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                        int count = 0;
//                        String key = null;
//                        for (DataSnapshot child : children) {
//                            SourceReport childValue = child.getValue(SourceReport.class);
//                            if (childValue.getId() == sourceReport.getId()) {
//                                key = child.getKey();
//                            }
//                            count++;
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//                if (!conditionSpinner.getSelectedItem().toString().equals(sourceReport.getCondition())) {
//                    databaseReference.child("source").child(key).child("condition").setValue(conditionSpinner.getSelectedItem().toString());
//                } else if (!dateText.getText().toString().equals(sourceReport.getDate())) {
//                    databaseReference.child("source").child(key).child("date").setValue(dateText.getText().toString());
//                } else if (!latitude.getText().toString().equals(sourceReport.getLatitude())) {
//                    databaseReference.child("source").child(key).child("latitude").setValue(Double.parseDouble(latitude.getText().toString()));
//                } else if (!longitude.getText().toString().equals(sourceReport.getLongitude())) {
//                    databaseReference.child("source").child(key).child("longitude").setValue(Double.parseDouble(longitude.getText().toString()));
//                } else if (!conditionSpinner.getSelectedItem().toString().equals(sourceReport.getCondition())) {
//                    databaseReference.child("source").child(key).child("waterType").setValue(typeSpinner.getSelectedItem().toString());
//                }

                if (currentUser.getUserType() == Standing.USER) {
                    startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
                } else if (currentUser.getUserType() == Standing.WORKER) {
                    startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                } else if (currentUser.getUserType() == Standing.MANAGER) {
                    startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                }
            }
        });

    }
}
