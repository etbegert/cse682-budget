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
    private AccountInfo accountInfo;
    public NewSavingsActivity(){
        this.accountInfo = SavingsFragment.accountInfo;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_savings);
    }
    /*Gets called from the button press and collects the data in each field*/
    public void assignSavings(View v)
    {
        EditText savingsName = (EditText) findViewById(R.id.savings_name);
        EditText savingsAmount = (EditText) findViewById(R.id.savings_amount);
        String amount = "$" + savingsAmount.getText().toString().replaceAll("[^\\d.]", "");
        if (amount.equals("$"))
            amount = "0";
        accountInfo.addSavings(createSavings(savingsName.getText().toString(),amount));
        finish();
    }
    /*Takes the data that is passed to it and puts it in the hash map for a savings account*/
    private HashMap<String, Serializable> createSavings(String name, String amount) {
        HashMap<String, Serializable> savings = new HashMap<>();
        savings.put("name",name);
        savings.put("amount", amount);
        return savings;
    }
}