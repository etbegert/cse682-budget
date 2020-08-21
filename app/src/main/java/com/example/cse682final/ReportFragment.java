package com.example.cse682final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d(DEBUG, "entered onCreate view");
        View rootView=inflater.inflate(R.layout.report_fragment,container,false);

        return rootView;
    }
}