package com.example.cse682final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public ViewHolder (View view){
            super(view);
            savings_name = (TextView)view.findViewById(R.id.savings_name);
            savings_amount = (TextView)view.findViewById(R.id.savings_amount);
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
        holder.savings_amount.setText(savings.get(position).get("amount").toString());
     }
    public void updateList(List<Map<String, ?>> postList) {
        this.savings = postList;
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
