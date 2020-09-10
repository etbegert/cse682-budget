package com.example.cse682final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class ReportListFragment extends Fragment {

    public interface OnItemSelectedListener{
        public void onListItemSelected(View sharedView, String income,String date,String type,String line1,String line2,String line3);
    }
    private final int NEW_REPORT_ACTIVITY = 13;
    public static AccountInfo accountInfo;
    OnItemSelectedListener clickListener;
    private ReportsAdapter myRecyclerAdaptor;
    public ReportListFragment(AccountInfo accountInfo)
    {
        this.accountInfo = accountInfo;
        myRecyclerAdaptor = new ReportsAdapter(accountInfo.getReportsList());
    }
    /*Initialize the recycler view and adapter for the report list*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.report_list_fragment,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView rv = rootView.findViewById(R.id.mainRecyclerView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myRecyclerAdaptor);
        rv.setItemAnimator((new DefaultItemAnimator()));

        FloatingActionButton fab = rootView.findViewById(R.id.reportFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newReport = new Intent(getContext(), NewReportActivity.class);
                startActivityForResult(newReport,NEW_REPORT_ACTIVITY);
            }
        });
        myRecyclerAdaptor.updateList(accountInfo.getReportsList());

        return rootView;
    }
    public void onAttach(@NotNull Context context){
        super.onAttach(context);
        try {
            clickListener=(OnItemSelectedListener)context;
            myRecyclerAdaptor.setOnListItemClickListener(clickListener);
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException(context.toString()+"must implement EventTraack");
        }

    }
    @Override
    public void onResume() {
        myRecyclerAdaptor.updateList(accountInfo.getReportsList());
        super.onResume();
    }

}