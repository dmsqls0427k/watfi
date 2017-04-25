package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.danae.wat2340.Model.SourceReport;
import com.example.danae.wat2340.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class viewSourceReport extends AppCompatActivity {
    private Button map, getList;
    private ListView lv;
    public static List<SourceReport> reportList = new ArrayList<>();
    static int placed = 0;
    public static SourceReport sourceReport;
    private static final String TAG = "View Source Report :";

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_source_report);
        lv = (ListView) findViewById(R.id.listView);
        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        getList = (Button) findViewById(R.id.getList);

        getList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("source").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                        for (DataSnapshot child : children) {
                            SourceReport childValue = child.getValue(SourceReport.class);
                            if (!reportList.contains(childValue)) {
                                reportList.add(childValue);
                            }
                            Log.d(TAG, childValue.toString() + " is the source report");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                List<String> reportString = new ArrayList<>();
                for (SourceReport r : reportList) {
                    reportString.add(r.toString());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(viewSourceReport.this,
                        android.R.layout.simple_list_item_1, reportString);
                lv.setAdapter(arrayAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        placed = position;
                        databaseReference.child("source").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                SourceReport selected = reportList.get(placed);

                                for (DataSnapshot child : children) {
                                    SourceReport childValue = child.getValue(SourceReport.class);

                                    if (childValue.getId() == selected.getId()) {
                                        sourceReport = childValue;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        startActivity(new Intent(getApplicationContext(), EditSourceReport.class));
                    }
                });
            }
        });

    }
}
