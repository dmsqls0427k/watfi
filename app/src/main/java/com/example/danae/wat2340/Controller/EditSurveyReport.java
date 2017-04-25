package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.danae.wat2340.Model.Population;
import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.Model.WaterType;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;
import static com.example.danae.wat2340.Controller.ViewSurveyReport.surveyReport;

public class EditSurveyReport extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    SourceReport selected;

    Button submitBtn, cancelBtn;
    TextView nameText, countText, dateText;
    EditText longitude, latitude;
    Spinner populationSpinner, typeSpinner;

    String key;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey_report);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        nameText = (TextView) findViewById(R.id.nameText);
        countText = (TextView) findViewById(R.id.countText);
        dateText = (TextView) findViewById(R.id.dateText);

        longitude = (EditText) findViewById(R.id.longitude);
        latitude = (EditText) findViewById(R.id.latitude);

        populationSpinner = (Spinner) findViewById(R.id.populationSpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);

        List<String> types = new ArrayList<String>();
        for (int i = 0; i < WaterType.values().length; i++) {
            types.add(WaterType.values()[i].toString());
        }
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        typeSpinner.setAdapter(typesAdapter);
        List<String> conditions = new ArrayList<String>();
        for (int i = 0; i < Population.values().length; i++) {
            conditions.add(Population.values()[i].toString());
        }
        ArrayAdapter<Population> adapter = new ArrayAdapter<Population>(
                this, android.R.layout.simple_spinner_item,
                Arrays.asList(Population.values()));
        populationSpinner.setAdapter(adapter);

        longitude.setText(surveyReport.getLongitude());
        latitude.setText(surveyReport.getLatitude());
        nameText.setText(surveyReport.getName());
        countText.setText(surveyReport.getId());
        dateText.setText(date);

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
