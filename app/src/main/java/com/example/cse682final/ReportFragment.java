package com.example.cse682final;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportFragment extends Fragment {

    private List<Map<String, ?>>  reports = new ArrayList<Map<String,?>>(); //This will change once the reports class is made
    public interface OnItemSelectedListener{
        public void onListItemSelected(View sharedView, int imageResourceID, String title, String year, Float rating, String description);
    }
    OnItemSelectedListener clickListener;
    private final MyRecyclerAdapter myRecyclerAdaptor=new MyRecyclerAdapter(reports);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d(DEBUG, "entered onCreate view");
        View rootView=inflater.inflate(R.layout.report_fragment,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView rv = rootView.findViewById(R.id.mainRecyclerView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myRecyclerAdaptor);
        rv.setItemAnimator((new DefaultItemAnimator()));

        return rootView;
    }
    public void onAttach(@NotNull Context context){
        super.onAttach(context);
       // try {
           // clickListener=(OnItemSelectedListener)context;
            //myRecyclerAdaptor.setOnListItemClickListener(clickListener);
       // }
       // catch (ClassCastException ex)
       // {
        //    throw new ClassCastException(context.toString()+"must implement EventTraack");
       // }
    }
}