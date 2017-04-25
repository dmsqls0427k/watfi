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

import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.Model.WaterCondition;
import com.example.danae.wat2340.Model.WaterType;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;

public class AddSourceReport extends AppCompatActivity {

    EditText latitude, longitude;
    TextView numberText, nameText, dateText;
    Spinner conditionSpinner, typeSpinner;
    Button submitBtn, cancelBtn;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("source");

    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soure_report);

        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        numberText = (TextView) findViewById(R.id.countText);
        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        List<String> types = new ArrayList<String>();
        for (int i = 0; i < WaterType.values().length; i++) {
            types.add(WaterType.values()[i].toString());
        }
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        typeSpinner.setAdapter(typesAdapter);
        List<String> conditions = new ArrayList<String>();
        for (int i = 0; i < WaterCondition.values().length; i++) {
            conditions.add(WaterCondition.values()[i].toString());
        }
        ArrayAdapter<String> conditionsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conditions);
        conditionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionsAdapter);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

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

        numberText.setText("" + count++);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude.getText().toString() == null || longitude.getText().toString() == null) {
                    Toast.makeText(AddSourceReport.this, "Location field is empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    SourceReport sourceReport = new SourceReport(dateText.getText().toString(),
                            count, nameText.getText().toString(), latitude.getText().toString(),
                            longitude.getText().toString(),
                            conditionSpinner.getSelectedItem().toString(),
                            typeSpinner.getSelectedItem().toString());
                    addReport(sourceReport);
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

    private void addReport(final SourceReport sourceReport) {
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(sourceReport);
        Toast toast = Toast.makeText(getApplicationContext(), "Submission Successful!", Toast.LENGTH_SHORT);
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
