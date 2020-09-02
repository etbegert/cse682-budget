package com.example.cse682final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportListFragment extends Fragment {


    public interface OnItemSelectedListener{
        public void onListItemSelected(View sharedView, String income,String date,String type,String line1,String line2,String line3);
    }
    private final int NEW_REPORT_ACTIVITY = 13;
    OnItemSelectedListener clickListener;
    private final MyRecyclerAdapter myRecyclerAdaptor=new MyRecyclerAdapter(AccountInfo.reports);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d(DEBUG, "entered onCreate view");
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
        myRecyclerAdaptor.updateList(AccountInfo.reports);

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
        myRecyclerAdaptor.updateList(AccountInfo.reports);
        super.onResume();
    }

}