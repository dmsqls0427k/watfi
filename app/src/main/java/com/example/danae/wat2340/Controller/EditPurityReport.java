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

import com.example.danae.wat2340.Model.OverallCondition;
import com.example.danae.wat2340.Model.PurityReport;
import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Date;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;
import static com.example.danae.wat2340.Controller.viewPurityReport.purityReport;

public class EditPurityReport extends AppCompatActivity {

    EditText contaminationText, virusText, locationText;
    TextView nameText, dateText, numberText;
    Spinner conditionSpinner;
    Button cancelBtn, submitBtn;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    String key;

    SourceReport selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purity_report);

        contaminationText = (EditText) findViewById(R.id.contaminationText);
        virusText = (EditText) findViewById(R.id.virusText);
        locationText = (EditText) findViewById(R.id.locationEdit);

        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);
        numberText = (TextView) findViewById(R.id.numberText);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);

        ArrayAdapter<OverallCondition> conditionAdapter = new ArrayAdapter<OverallCondition>(
                this, android.R.layout.simple_spinner_item, Arrays.asList(OverallCondition.values()));
        conditionSpinner.setAdapter(conditionAdapter);

        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        nameText.setText(purityReport.getName());
        virusText.setText("" + purityReport.getVirusPPM());
        contaminationText.setText("" + purityReport.getContaminantPPM());
        dateText.setText(date);
        locationText.setText(purityReport.getLocation());

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
//                databaseReference.child("purity").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                        int count = 0;
//                        String key = null;
//                        for (DataSnapshot child : children) {
//                            PurityReport childValue = child.getValue(PurityReport.class);
//                            if (childValue.getId() == purityReport.getId()) {
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
//                if (!conditionSpinner.getSelectedItem().toString().equals(purityReport.getCondition())) {
//                    databaseReference.child("purity").child(key).child("condition").setValue(conditionSpinner.getSelectedItem().toString());
//                } else if (!dateText.getText().toString().equals(purityReport.getDate())) {
//                    databaseReference.child("purity").child(key).child("date").setValue(dateText.getText().toString());
//                } else if (!locationText.getText().toString().equals(purityReport.getLocation())) {
//                    databaseReference.child("purity").child(key).child("location").setValue(locationText.getText().toString());
//                } else if (!virusText.getText().toString().equals(purityReport.getVirusPPM())) {
//                    databaseReference.child("purity").child(key).child("virusPPM").setValue(Double.parseDouble(virusText.getText().toString()));
//                } else if (!contaminationText.getText().toString().equals(purityReport.getContaminantPPM())) {
//                    databaseReference.child("purity").child(key).child("contaminationPPM").setValue(Double.parseDouble(contaminationText.getText().toString()));
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
