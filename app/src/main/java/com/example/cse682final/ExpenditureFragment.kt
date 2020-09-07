package com.example.cse682final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExpenditureFragment(var accountInfo: AccountInfo) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.expenditure_fragment, container, false)
        v.findViewById<Button>(R.id.expEntrySubmit).setOnClickListener {
            accountInfo.addExpenditure(v.findViewById<EditText>(R.id.expEntry).text.toString().toFloat())
        }
        v.findViewById<TextView>(R.id.expTotalIncome).text = accountInfo.income.toString()
        v.findViewById<TextView>(R.id.expTotalExpenditures).text = accountInfo.expenditureTotal.toString()
        v.findViewById<TextView>(R.id.expTotalRemaining).text = (accountInfo.income - accountInfo.expenditureTotal).toString()
        val rv = v.findViewById<RecyclerView>(R.id.expenditureRecycler)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.scrollToPosition(0)
        rv.layoutManager = layoutManager
        rv.adapter = ExpenditureAdapter(accountInfo)
        rv.itemAnimator = DefaultItemAnimator()
        return v
    }

}