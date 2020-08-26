package com.example.cse682final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    private List<Map<String, ?>> reports;
    private ReportFragment.OnItemSelectedListener clickListener=null;
    public MyRecyclerAdapter(List<Map<String, ?>> list)
    {
        reports=list;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView report_name;
        public TextView report_date;
        public TextView report_type;
        public ViewHolder (View view){
            super(view);
            report_name = (TextView)view.findViewById(R.id.report_name);
            report_date = (TextView)view.findViewById(R.id.report_date);
            report_type = (TextView)view.findViewById(R.id.report_type);
        }
    }


    public void setOnListItemClickListener(ReportFragment.OnItemSelectedListener listener)
    {
        clickListener= listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }
    @Override
    public void onBindViewHolder(final MyRecyclerAdapter.ViewHolder holder,final int position)
    {
        holder.report_name.setText(reports.get(position).get("name").toString());
        holder.report_date.setText(reports.get(position).get("date").toString());
        holder.report_type.setText(reports.get(position).get("type").toString());
        holder.report_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.Companion.getMContext(),"Open Report",Toast.LENGTH_LONG).show();
                }
        });
     }

    @Override
    public int getItemCount() {
        return reports.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {

    }


}
