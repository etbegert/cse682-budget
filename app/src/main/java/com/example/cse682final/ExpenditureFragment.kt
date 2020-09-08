package com.example.cse682final

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ExpenditureFragment(val accountInfo: AccountInfo) : Fragment() {

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var ref = FirebaseDatabase.getInstance().getReference("Users").child(currentUser?.uid!!)
    private val recycleAdapter = ExpenditureAdapter(accountInfo.expenditureList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.expenditure_fragment, container, false)
        v.findViewById<TextView>(R.id.expTotalIncome).text = accountInfo.income.toString()
        v.findViewById<TextView>(R.id.expTotalExpenditures).text = accountInfo.expenditureTotal.toString()
        v.findViewById<TextView>(R.id.expTotalRemaining).text = (accountInfo.income.toFloat() - accountInfo.expenditureTotal).toString()
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
            }
            else {
                Toast.makeText(v.context, "Not a valid number, please try again.",Toast.LENGTH_SHORT).show()
            }
        }
        recycleAdapter.updateList(accountInfo.expenditureList)
        return v
    }
}