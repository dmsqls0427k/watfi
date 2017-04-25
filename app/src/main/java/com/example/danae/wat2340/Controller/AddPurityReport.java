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

import com.example.danae.wat2340.Model.OverallCondition;
import com.example.danae.wat2340.Model.PurityReport;
import com.example.danae.wat2340.Model.Standing;
import com.example.danae.wat2340.Model.Virus;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Date;

import static com.example.danae.wat2340.Controller.LoginActivity.currentUser;

public class AddPurityReport extends AppCompatActivity {

    EditText contaminationText, virusText, locationText;
    TextView nameText, dateText, numberText;
    Spinner conditionSpinner, virusSpinner;
    Button cancelBtn, submitBtn;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    public final String date = sdf.format(new Date());

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getInstance().getReference("purity");

    int count = 0;

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
        virusSpinner = (Spinner) findViewById(R.id.virusSpinner);
        final ArrayAdapter<Virus> virusArrayAdapter = new ArrayAdapter<Virus>(this,
                android.R.layout.simple_spinner_item, Arrays.asList(Virus.values()));
        virusSpinner.setAdapter(virusArrayAdapter);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        submitBtn = (Button) findViewById(R.id.submitBtn);
//        virusSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    virusText.setText("0.0");
//                }
//            }
//        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    PurityReport childValue = child.getValue(PurityReport.class);
                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        nameText.setText(currentUser.getName());
        dateText.setText(date);
        numberText.setText("" + count++);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contaminationText.getText() == null || virusText.getText() == null || locationText.getText() == null) {
                    Toast.makeText(AddPurityReport.this, "One or more field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    PurityReport purityReport = new PurityReport(dateText.getText().toString(),
                            count, nameText.getText().toString(), locationText.getText().toString(),
                            conditionSpinner.getSelectedItem().toString(), virusSpinner.getSelectedItem().toString(),
                            Double.parseDouble(virusText.getText().toString()), Double.parseDouble(contaminationText.getText().toString()));
                    addPurityReport(purityReport);
                }
            }
        });
    }

    private void addPurityReport(final PurityReport purityReport) {
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(purityReport);
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