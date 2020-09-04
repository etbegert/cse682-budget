package com.example.cse682final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;

public class EditSavingsActivity extends AppCompatActivity {
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_savings);
        Intent myIntent = getIntent();
        EditText amount = (EditText) findViewById(R.id.savings_amount);
        amount.setText(myIntent.getStringExtra("amount").replace("$",""));
        position = myIntent.getIntExtra("position",0);

    }

    public void editSavings(View v)
    {
        EditText savingsAmount = (EditText) findViewById(R.id.savings_amount);
        String amount = "$" + savingsAmount.getText().toString().replaceAll("[^\\d.]", "");
        HashMap<String, Serializable> account = new HashMap<>();
        account.put("name",AccountInfo.savings.get(position).get("name").toString());
        account.put("amount",amount);
        AccountInfo.savings.set(position,account);
        finish();
    }
}