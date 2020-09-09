package com.example.cse682final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class SavingsFragment extends Fragment {

    private final int NEW_SAVINGS_ACTIVITY = 13;
    private SavingsAdapter myRecyclerAdaptor;
    private TextView totalSavings = null;
    public static AccountInfo accountInfo;
    public SavingsFragment(AccountInfo accountInfo)
    {
        this.accountInfo = accountInfo;
        myRecyclerAdaptor=new SavingsAdapter(accountInfo.getSavingsList());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.savings_fragment,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());


        RecyclerView rv = rootView.findViewById(R.id.mainRecyclerView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myRecyclerAdaptor);
        rv.setItemAnimator((new DefaultItemAnimator()));

        FloatingActionButton fab = rootView.findViewById(R.id.savingsFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSavings = new Intent(getContext(), NewSavingsActivity.class);
                startActivityForResult(newSavings,NEW_SAVINGS_ACTIVITY);
            }
        });
        myRecyclerAdaptor.updateList(accountInfo.getSavingsList());

        totalSavings = (TextView) rootView.findViewById(R.id.total_savings);
        updateTotal();
        return rootView;
    }

    public void onAttach(@NotNull Context context){
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        myRecyclerAdaptor.updateList(accountInfo.getSavingsList());
        updateTotal();
        Log.d("DEBUG","Total savings is " + accountInfo.getSavingsTotal());
        super.onResume();
    }

    public float getTotalSavings(){
        float total = 0;
        for (int i = 0; i < accountInfo.getSavingsList().size(); i++)
        {
            total = total + Float.parseFloat(accountInfo.getSavingsList().get(i).get("amount").toString().replace("$",""));
        }
        return total;
    }
    public void updateTotal(){

        accountInfo.setSavingsTotal(getTotalSavings());
        totalSavings.setText("Total: $" + String.format("%,.2f",Float.parseFloat((String.valueOf(accountInfo.getSavingsTotal())))));
        accountInfo.updateDatabase();
    }

}
