package com.example.cse682final;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class NewReportActivity extends AppCompatActivity {
    private Spinner spinner;
    private String date;
    private String income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_report);
        spinner = (Spinner) findViewById(R.id.report_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.reports_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        date = android.text.format.DateFormat.format("yyyy-MM-dd hh:mm a", new java.util.Date()).toString();
        income = "1000";

    }

    public void assignReport(View view)
    {

        Toast.makeText(this,"Generating your report",Toast.LENGTH_LONG).show();
        int reportType = spinner.getSelectedItemPosition();
        switch(reportType) {
            case 0:
                genIncomeBills();
                break;
            case 1:
                genIncomeExpend();
                break;
            case 2:
                genSavings();
                break;
            default:
                // code block
        }
        finish();

    }

    private void genIncomeBills()
    {
        AccountInfo.reports.add(createReport("Income to Bills", "Line 1", "Line 2", "Line 3"));
    }
    private void genIncomeExpend()
    {
        AccountInfo.reports.add(createReport("Income to Expendature", "Line 1", "Line 2", "Line 3"));
    }
    private void genSavings()
    {
        AccountInfo.reports.add(createReport("Savings Projection", "Line 1", "Line 2", "Line 3"));
    }
    private HashMap<String, Serializable> createReport(String type, String line1, String line2, String line3) {
        HashMap<String, java.io.Serializable> report = new HashMap<>();
        report.put("type",type);
        report.put("date", date);
        report.put("income",income);
        report.put("line1", line1);
        report.put("line2", line2);
        report.put("line3",line3);
        return report;
    }
}