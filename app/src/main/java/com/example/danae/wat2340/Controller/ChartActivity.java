package com.example.danae.wat2340.Controller;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.danae.wat2340.Model.Data;
import com.example.danae.wat2340.Model.PurityReport;
import com.example.danae.wat2340.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartActivity extends AppCompatActivity {
    private static int year = 2017;
    private Button settings;
    private static String virus = "Norovirus";
    private GoogleApiClient client;
    private List<Data> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        settings = (Button) findViewById(R.id.graphSettings);
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        int[] reportNum = new int[12];
        //dummy
        for (int i = 0; i < 12; i++) {
            Random rand = new Random();
            reportNum[i] += rand.nextInt(5);
        }

        for (PurityReport r: viewPurityReport.purityReports) {
            String date = r.getDate();
            String[] d = date.split("/");
            if (d[0].equals("" + year)) {
                int month = Integer.parseInt(d[1]) - 1;
                reportNum[month]++;
            }
        }
        int monthly = 1;
        for (int d: reportNum) {
            data.add(new Data(monthly, d));
            monthly++;
        }
        List<Entry> entries = convertDataSetToEntry(data);
        LineDataSet dataSet = new LineDataSet(entries, "# of Purity Reports");
        Log.d("APP", "Made data set with: " + entries.size());
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawFilled(true);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Number of Reports with " + virus + " in " + year);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private List<Entry> convertDataSetToEntry(List<Data> data) {
        List<Entry> entries = new ArrayList<>();

        for (Data d : data) {
            entries.add(new Entry(d.x, (int) d.y));
        }

        return entries;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Chart Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
