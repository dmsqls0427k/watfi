package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danae.wat2340.R;

import java.util.ArrayList;
import java.util.List;

public class ManagerMainActivity extends AppCompatActivity {
    private ListView _managerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        _managerList = (ListView) findViewById(R.id.managerList);
        List<String> menu = new ArrayList<>();
        menu.add("View Profile");
        menu.add("Add Source Report");
        menu.add("View Source Report");
        menu.add("Add Purity Report");
        menu.add("View Purity Report");
<<<<<<< HEAD
        menu.add("Add Survey Report");
=======
>>>>>>> fce46b4425e401d0f55e088633e67fa36b5fcbc9
        menu.add("View Survey Report");
        menu.add("Log out");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu);

        _managerList.setAdapter(arrayAdapter);
        _managerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(ManagerMainActivity.this, ViewProfile.class));
                } else if (position == 1) {
                    startActivity(new Intent(ManagerMainActivity.this, AddSourceReport.class));
                } else if(position == 2) {
                    startActivity(new Intent(ManagerMainActivity.this, viewSourceReport.class));
                } else if(position == 3) {
                    startActivity(new Intent(ManagerMainActivity.this, AddPurityReport.class));
                } else if (position == 4) {
                    startActivity(new Intent(ManagerMainActivity.this, viewPurityReport.class));
                } else if (position == 5) {
<<<<<<< HEAD
                    startActivity(new Intent(ManagerMainActivity.this, AddSurveyReport.class));
                } else if (position == 6) {
=======
>>>>>>> fce46b4425e401d0f55e088633e67fa36b5fcbc9
                    startActivity(new Intent(ManagerMainActivity.this, ViewSurveyReport.class));
                } else {
                    startActivity(new Intent(ManagerMainActivity.this, LoginActivity.class));
                }

            }
        });
    }
}
