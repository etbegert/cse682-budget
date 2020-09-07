package com.example.cse682final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SummaryFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Log.d(DEBUG, "entered onCreate view");
        return inflater.inflate(R.layout.summary_fragment, container, false)
    }
}