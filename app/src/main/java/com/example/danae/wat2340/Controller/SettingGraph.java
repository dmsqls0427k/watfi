package com.example.danae.wat2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

import com.example.danae.wat2340.R;
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

    }
}
