package com.example.cse682final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ViewHolder>{

    private List<Map<String, ?>> reports;
    private ReportListFragment.OnItemSelectedListener clickListener=null;
    private ReportFragment rf = new ReportFragment();
    public ReportsAdapter(List<Map<String, ?>> list)
    {
        reports=list;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView report_name;
        public TextView report_date;
        public TextView report_type;
        public ImageButton delete_button;
        public ViewHolder (View view){
            super(view);
            report_name = (TextView)view.findViewById(R.id.report_name);
            report_date = (TextView)view.findViewById(R.id.report_date);
            report_type = (TextView)view.findViewById(R.id.report_type);
            delete_button = (ImageButton)view.findViewById(R.id.delete_button);
        }
    }

    public void setOnListItemClickListener(ReportListFragment.OnItemSelectedListener listener)
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
    public void onBindViewHolder(final ReportsAdapter.ViewHolder holder, final int position)
    {
        String name = "Report " + position;
        holder.report_name.setText(name);
        holder.report_date.setText(reports.get(position).get("date").toString());
        holder.report_type.setText(reports.get(position).get("type").toString());
        /*Set up listener to display report on click of the name*/
        holder.report_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.Companion.getMContext(),"Open Report",Toast.LENGTH_LONG).show();
                    if(clickListener!=null) {
                        clickListener.onListItemSelected(view, reports.get(position).get("income").toString(),
                                reports.get(position).get("date").toString(),
                                reports.get(position).get("type").toString(),
                                reports.get(position).get("line1").toString(),
                                reports.get(position).get("line2").toString(),
                                reports.get(position).get("line3").toString());
                    }
                }
        });
        /*Set up listener to delete report on click of the image*/
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reports.remove(position);
                ReportListFragment.accountInfo.setReports(reports);
                updateList(reports);
            }
        });
     }
     /*Pass in new report list to update the adapter*/
    public void updateList(List<Map<String, ?>> postList) {
        this.reports = postList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}
