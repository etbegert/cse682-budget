package com.example.cse682final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryFragment(var accountInfo: AccountInfo) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.summary_fragment, container, false)
        v.findViewById<TextView>(R.id.incomeDisplay).text = "$" + String.format("%,.2f",accountInfo.income.toFloat())
        v.findViewById<TextView>(R.id.expendituresDisplay).text = "$" + String.format("%,.2f",accountInfo.expenditureTotal)
        v.findViewById<TextView>(R.id.versusDisplay).text = String.format("%,.2f",accountInfo.income/accountInfo.expenditureTotal) + "%"
        v.findViewById<TextView>(R.id.reportsDisplay).text = "${accountInfo.reportsList.size}"
        v.findViewById<TextView>(R.id.savingsDisplay).text = "$" + String.format("%,.2f",accountInfo.savingsTotal.toFloat())



        return v
    }
}