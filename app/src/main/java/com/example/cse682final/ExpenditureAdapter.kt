package com.example.cse682final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class ExpenditureAdapter(val accountInfo: AccountInfo) : RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>() {
    var expenditureList: ArrayList<Float>
    init {
        expenditureList = accountInfo.expenditureList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expenditureValue: TextView = view.findViewById(R.id.expenditure_value)
        val expenditureDelete: AppCompatImageButton = view.findViewById(R.id.expenditure_delete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenditureAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.expenditure_card_view_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExpenditureAdapter.ViewHolder, position: Int) {
        holder.expenditureValue.text = ("$" + String.format("%,.2f",expenditureList[position].toString().toFloat()))
        holder.expenditureDelete.setOnClickListener {
            accountInfo.deleteExpenditure(position)
            notifyDataSetChanged()
            val savingsFragment = MainActivity.fragmentManager.findFragmentByTag("Expenditures") as ExpenditureFragment
            savingsFragment.updateDisplay()
        }
    }

    override fun getItemCount(): Int {
        return this.expenditureList.size
    }

    fun updateList(expenditureList: ArrayList<Float>) {
        this.expenditureList = expenditureList
        this.notifyDataSetChanged()
    }
}