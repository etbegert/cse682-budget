package com.example.cse682final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ReportFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.report_fragment,container,false);

        assert getArguments() != null;
        String type = getArguments().getString("type");
        String date = "Date: " + getArguments().getString("date");
        String income = "Income: " + getArguments().getString("income");
        String line1 = getArguments().getString("line1");
        String line2 = getArguments().getString("line2");
        String line3 = getArguments().getString("line3");
        /*Populate each view*/
        TextView t = rootView.findViewById(R.id.type);
        t.setText(type);
        TextView d = rootView.findViewById(R.id.date);
        d.setText(date);
        TextView i = rootView.findViewById(R.id.income);
        i.setText(income);
        TextView l1 = rootView.findViewById(R.id.line1);
        l1.setText(line1);
        TextView l2 = rootView.findViewById(R.id.line2);
        l2.setText(line2);
        TextView l3 = rootView.findViewById(R.id.line3);
        l3.setText(line3);

        return rootView;
    }




}
