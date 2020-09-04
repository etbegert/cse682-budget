package com.example.cse682final;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class NewSavingsActivity extends AppCompatActivity {
    private Spinner spinner;
    private String date;
    private float income;
    private float bills;
    private float expenditures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_savings);
    }

    public void assignSavings(View v)
    {
        EditText savingsName = (EditText) findViewById(R.id.savings_name);
        EditText savingsAmount = (EditText) findViewById(R.id.savings_amount);
        String amount = "$" + savingsAmount.getText().toString().replaceAll("[^\\d.]", "");
        if (amount.equals("$"))
            amount = "0";
        AccountInfo.savings.add(createSavings(savingsName.getText().toString(),amount));
        finish();
    }
    private HashMap<String, Serializable> createSavings(String name, String amount) {
        HashMap<String, Serializable> report = new HashMap<>();
        report.put("name",name);
        report.put("amount", amount);
        return report;
    }
}