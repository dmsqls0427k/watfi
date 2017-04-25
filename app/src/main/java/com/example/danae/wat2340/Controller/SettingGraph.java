package com.example.danae.wat2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.danae.wat2340.R;

import java.util.ArrayList;
import java.util.List;

public class SettingGraph extends AppCompatActivity {
    private Spinner yearSpinner, virusSpinner;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_graph);
        yearSpinner = (Spinner) findViewById(R.id.YearSpinnerSet);
        virusSpinner = (Spinner) findViewById(R.id.virusSpinnerSet);
        change = (Button) findViewById(R.id.change);
        List<String> dumYear = new ArrayList<>();
        dumYear.add("2015");
        dumYear.add("2016");
        dumYear.add("2017");
        ArrayAdapter yearAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dumYear);
        yearSpinner.setAdapter(yearAdapter);
        ArrayAdapter vAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ChartActivity.vList);
        virusSpinner.setAdapter(vAdapter);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVirus = virusSpinner.getSelectedItem().toString();
                ChartActivity.virus = newVirus;
                ChartActivity.year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
                startActivity(new Intent(SettingGraph.this, ChartActivity.class));
            }
        });

    }
}
