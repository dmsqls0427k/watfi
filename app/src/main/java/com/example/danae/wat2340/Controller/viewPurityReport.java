package com.example.danae.wat2340.Controller;

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.danae.wat2340.Model.PurityReport;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewPurityReport extends AppCompatActivity {
    ListView lv;
    Button getList, getGraph;
    static List<PurityReport> reportList = new ArrayList<>();
    static int placed = 0;
    public static PurityReport purityReport;
    public static List<PurityReport> purityReports;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purity_report);
        purityReports = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listView);
        getList = (Button) findViewById(R.id.getList);
        getGraph = (Button) findViewById(R.id.graphBtn);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        getGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (purityReports.size() > 0) {
                    startActivity(new Intent(viewPurityReport.this, ChartActivity.class));
                } else {
                    Toast.makeText(viewPurityReport.this, "You need more than one data"
                            + "to make a graph.",
                            Toast.LENGTH_LONG).show();
                }
            }});
        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("purity").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        for (DataSnapshot child : children) {
                            PurityReport childValue = child.getValue(PurityReport.class);
                            if (!purityReports.contains(childValue)) {
                                purityReports.add(childValue);
                            }
                            reportList.add(childValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                List<String> reportString = new ArrayList<>();
                for (PurityReport r : reportList) {
                    reportString.add(r.toString());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(viewPurityReport.this,
                        android.R.layout.simple_list_item_1, reportString);
                lv.setAdapter(arrayAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        placed = position;
                        databaseReference.child("purity").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                PurityReport selected = reportList.get(placed);

                                for (DataSnapshot child : children) {
                                    PurityReport childValue = child.getValue(PurityReport.class);
                                    if (childValue.getId() == selected.getId()) {
                                        purityReport = childValue;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        startActivity(new Intent(getApplicationContext(), EditPurityReport.class));
                    }
                });
            }

        });



    }
}