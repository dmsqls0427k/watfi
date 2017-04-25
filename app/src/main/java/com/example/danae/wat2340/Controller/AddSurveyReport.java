package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danae.wat2340.Model.Population;
import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.Model.SurveyReport;
import com.example.danae.wat2340.Model.WaterType;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;

public class AddSurveyReport extends AppCompatActivity {

    Button submitBtn, cancelBtn;
    TextView nameText, countText, dateText;
    EditText longitude, latitude;
    Spinner populationSpinner, typeSpinner;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("survey");

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_survey_report);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        nameText = (TextView) findViewById(R.id.nameText);
        countText = (TextView) findViewById(R.id.countText);
        dateText = (TextView) findViewById(R.id.dateText);

        longitude = (EditText) findViewById(R.id.longitudeText);
        latitude = (EditText) findViewById(R.id.latitudeText);

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

        nameText.setText(currentUser.getName());
        dateText.setText(date);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    SourceReport childValue = child.getValue(SourceReport.class);
                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        countText.setText("" + count++);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude.getText() == null || longitude.getText() == null) {
                    Toast.makeText(AddSurveyReport.this, "Location field is empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    SurveyReport surveyReport = new SurveyReport(dateText.getText().toString(),
                            count, nameText.getText().toString(), (Population) populationSpinner.getSelectedItem(),
                             longitude.getText().toString(),
                            latitude.getText().toString(), typeSpinner.getSelectedItem().toString());
                    addReport(surveyReport);
                }
            }
        });

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
    }

    private void addReport(final SurveyReport surveyReport) {
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(surveyReport);
        Toast toast = Toast.makeText(getApplicationContext(), "Survey Submitted!", Toast.LENGTH_SHORT);
        toast.show();
        if (currentUser.getUserType() == Standing.USER) {
            startActivity(new Intent(getApplicationContext(), UserMainActivity.class));
        } else if (currentUser.getUserType() == Standing.WORKER) {
            startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
        } else if (currentUser.getUserType() == Standing.MANAGER) {
            startActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
        }
    }
}
