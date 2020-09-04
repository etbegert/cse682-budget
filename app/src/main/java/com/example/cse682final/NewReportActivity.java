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
    private float income;
    private float bills;
    private float expenditures;
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
        income = 50000; //TODO: Pull from account info
        bills = 400; //TODO: Pull from account info
        expenditures = 300; //TODO: Pull from account info

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
        float percentage = (bills/(income/12)) * 100;
        float tip1 = ((percentage/2)/100)*(income/12);
        float tip2 = ((percentage/4)/100)*(income/12);
        String line1 = "Percentage of income spent on bills monthly: " + String.format("%.2f", percentage) + "%";
        String line2 = "Amount spent on bills if percentage is halved: $" + String.format("%,.2f", tip1);
        String line3 = "Amount spent on bills if percentage is quartered: $" + String.format("%,.2f", tip2);
        AccountInfo.reports.add(createReport("Income to Bills", line1, line2, line3));
    }
    private void genIncomeExpend()
    {
        float percentage = (expenditures/(income/12)) * 100;
        float tip1 = ((percentage/2)/100)*(income/12);
        float tip2 = ((percentage/4)/100)*(income/12);
        String line1 = "Percentage of income spent on expenditures monthly: " + String.format("%.2f", percentage) + "%";
        String line2 = "Amount spent on expenditures if percentage is halved: $" + String.format("%,.2f", tip1);
        String line3 = "Amount spent on expenditures if percentage is quartered: $" + String.format("%,.2f", tip2);
        AccountInfo.reports.add(createReport("Income to Expenditure", line1, line2, line3));
    }
    private void genSavings()
    {
        float tip1 = (float) ((income * .1) + AccountInfo.savingsTotal);
        float tip2 = (float) ((income * .2) + AccountInfo.savingsTotal);
        String line1 = "Your total savings will be a balance of $" + String.format("%,.2f", tip1) + " in one year if 10% of income is saved";
        String line2 = "Your total savings will be a balance of $" + String.format("%,.2f", tip2) + " in one year if 20% of income is saved";
        AccountInfo.reports.add(createReport("Savings Projection", line1, line2, ""));
    }
    private HashMap<String, Serializable> createReport(String type, String line1, String line2, String line3) {
        HashMap<String, java.io.Serializable> report = new HashMap<>();
        report.put("type",type);
        report.put("date", date);
        report.put("income",String.format("%,.2f", income));
        report.put("line1", line1);
        report.put("line2", line2);
        report.put("line3",line3);
        return report;
    }
}