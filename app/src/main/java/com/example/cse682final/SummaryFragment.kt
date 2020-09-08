package com.example.cse682final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryFragment(var accountInfo: AccountInfo) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.summary_fragment, container, false)
        v.findViewById<TextView>(R.id.incomeDisplay).text = accountInfo.income.toString()
        v.findViewById<TextView>(R.id.expendituresDisplay).text = accountInfo.expenditureTotal.toString()
        v.findViewById<TextView>(R.id.versusDisplay).text = "${accountInfo.income/accountInfo.expenditureTotal}% of income spent"
        v.findViewById<TextView>(R.id.reportsDisplay).text = "This one is for Jeremy"
        v.findViewById<TextView>(R.id.savingsDisplay).text = "This one is for Jeremy"

        return v
    }
}