package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danae.wat2340.R;

import java.util.ArrayList;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {
    ListView userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        userList = (ListView) findViewById(R.id.userList);
        List<String> menu = new ArrayList<>();
        menu.add("View Profile");
        menu.add("Add Source Report");
        menu.add("View Source Report");
        menu.add("Submit Survey Report");
        menu.add("Log out");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu);
        setContentView(R.layout.activity_manager_main);
        userList.setAdapter(arrayAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(UserMainActivity.this, ViewProfile.class));
                } else if (position == 1) {
                    startActivity(new Intent(UserMainActivity.this, AddSourceReport.class));
                } else if(position == 2) {
                    startActivity(new Intent(UserMainActivity.this, viewSourceReport.class));
                } else if (position == 3) {
                    startActivity(new Intent(UserMainActivity.this, AddSurveyReport.class));
                } else {
                    startActivity(new Intent(UserMainActivity.this, LoginActivity.class));
                }

            }
        });
    }
}
