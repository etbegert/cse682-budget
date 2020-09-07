package com.example.cse682final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenditureAdapter(var expenditureList: ArrayList<Float>) : RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var expenditureValue: TextView = view.findViewById<TextView>(R.id.expenditureValue)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenditureAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expenditure_card_view_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ExpenditureAdapter.ViewHolder, position: Int) {
        holder.expenditureValue.text = expenditureList[position].toString()
    }

    override fun getItemCount(): Int {
        return this.expenditureList.size
    }

    fun updateList(expenditureList: ArrayList<Float>) {
        this.expenditureList = expenditureList
        this.notifyDataSetChanged()
    }
}