package com.example.cse682final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ExpenditureFragment(val accountInfo: AccountInfo) : Fragment() {

    private val recycleAdapter = ExpenditureAdapter(accountInfo)
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.expenditure_fragment, container, false)
        updateDisplay()
        val rv = v.findViewById<RecyclerView>(R.id.expenditureRecycler)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.scrollToPosition(0)
        rv.layoutManager = layoutManager
        rv.adapter = recycleAdapter
        rv.itemAnimator = DefaultItemAnimator()
        val expEntry = v.findViewById<EditText>(R.id.expEntry)
        v.findViewById<Button>(R.id.expEntrySubmit).setOnClickListener {
            if(expEntry.text.toString() != "" && expEntry.text.toString().toFloatOrNull() != null) {
                accountInfo.addExpenditure(v.findViewById<EditText>(R.id.expEntry).text.toString().toFloat())
                recycleAdapter.updateList(accountInfo.expenditureList)
                updateDisplay()
                v.findViewById<EditText>(R.id.expEntry).text.clear()
            }
            else {
                Toast.makeText(v.context, "Not a valid number, please try again.",Toast.LENGTH_SHORT).show()
            }
        }
        recycleAdapter.updateList(accountInfo.expenditureList)
        return v
    }

    fun updateDisplay() {
        v.findViewById<TextView>(R.id.expTotalIncome).text = "$" + String.format("%,.2f",accountInfo.income.toString().toFloat())
        v.findViewById<TextView>(R.id.expTotalExpenditures).text = "$" + String.format("%,.2f",accountInfo.expenditureTotal.toString().toFloat())
        v.findViewById<TextView>(R.id.expTotalRemaining).text = "$" + String.format("%,.2f",(accountInfo.income.toFloat() - accountInfo.expenditureTotal).toString().toFloat())
    }

}