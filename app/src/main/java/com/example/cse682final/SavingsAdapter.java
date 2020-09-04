package com.example.cse682final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class SavingsAdapter extends RecyclerView.Adapter<SavingsAdapter.ViewHolder>{

    private List<Map<String, ?>> savings;
    public SavingsAdapter(List<Map<String, ?>> list)
    {
        savings=list;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView savings_name;
        public TextView savings_amount;
        public ImageButton delete_button;
        public ViewHolder (View view){
            super(view);
            savings_name = (TextView)view.findViewById(R.id.savings_name);
            savings_amount = (TextView)view.findViewById(R.id.savings_amount);
            delete_button = (ImageButton)view.findViewById(R.id.delete_button);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.savings_card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }
    @Override
    public void onBindViewHolder(final SavingsAdapter.ViewHolder holder, final int position)
    {
        holder.savings_name.setText(savings.get(position).get("name").toString());
        holder.savings_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.mContext,EditSavingsActivity.class);
                myIntent.putExtra("amount",holder.savings_amount.getText().toString());
                myIntent.putExtra("position",position);
                MainActivity.mContext.startActivity(myIntent);
            }
        });
        holder.savings_amount.setText("$"+String.format("%,.2f",Float.parseFloat(savings.get(position).get("amount").toString().replace("$",""))));
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savings.remove(position);
                updateList(savings);
            }
        });
     }
    public void updateList(List<Map<String, ?>> postList) {
        this.savings = postList;
        SavingsFragment savingsFragment = (SavingsFragment) MainActivity.fragmentManager.findFragmentByTag("Savings");
        if (savingsFragment != null && savingsFragment.isVisible()) {
            savingsFragment.updateTotal();
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return savings.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {

    }


}
