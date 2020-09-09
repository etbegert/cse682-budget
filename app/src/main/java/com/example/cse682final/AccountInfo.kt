package com.example.cse682final

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import kotlin.collections.MutableMap

class AccountInfo() {
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val userRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser?.uid!!)
    var income: Float
    var expenditureList: ArrayList<Float>
    var expenditureTotal : Float
    var savingsList: MutableList<MutableMap<String, *>>
    var reportsList: MutableList<MutableMap<String, *>>
    var alerts: ArrayList<String>
    var savingsTotal: Float
    var billsTotal: Float

    init {
        income = 0F
        expenditureList = ArrayList<Float>()
        expenditureTotal = 0F
        savingsList = ArrayList()
        reportsList = ArrayList()
        alerts = ArrayList<String>()
        savingsTotal = 0F
        billsTotal = 0F
    }

    constructor(income: Float) : this() {
        this.income = income
    }

    constructor(
        income: Float,
        expenditureList: ArrayList<Float>,
        expenditureTotal: Float,
        savingsList: MutableList<MutableMap<String, *>>,
        reportsList: MutableList<MutableMap<String, *>>,
        alerts: ArrayList<String>,
        savingsTotal: Float,
        billsTotal: Float
    ) : this() {
        this.income = income
        this.expenditureList = expenditureList
        this.expenditureTotal = expenditureTotal
        this.savingsList = savingsList
        this.reportsList = reportsList
        this.alerts = alerts
        this.billsTotal = billsTotal
        this.savingsTotal = savingsTotal
    }

    // Set the user's income
    fun changeIncome(userIncome: Float) {
        this.income = userIncome
        updateDatabase()
    }
    // Add the user's expenditure
    fun addExpenditure(expend: Float) {
        this.expenditureList.add(expend)
        this.expenditureTotal += expend
        updateDatabase()
    }

    fun deleteExpenditure(position: Int) {
        this.expenditureTotal -= this.expenditureList[position]
        this.expenditureList.removeAt(position)
        updateDatabase()
    }

    // Add the user's savings
    fun addSavings(savings :HashMap<String, Serializable>) {
        this.savingsList.add(savings)
        updateDatabase()
    }

    // Add the user's alerts
    fun setAlert(alertsList: ArrayList<String>) {
        this.alerts = alertsList
        updateDatabase()
    }

    // Add the user's auto reports
    fun setReports(reports: MutableList<MutableMap<String, *>>) {
        this.reportsList = reports
        updateDatabase()
    }

    // Add the user's auto reports
    fun setSavings(savings: MutableList<MutableMap<String, *>>) {
        this.savingsList = savings
        updateDatabase()
    }

    // Get a specific expenditure
    fun getExpenditure(position: Int): Float {
        return expenditureList.get(position)
    }

    // Get the user's alerts
    fun getAlertsFun(): ArrayList<String> {
        return alerts
    }

    // Get the user's auto reports
    fun getReports(): MutableList<MutableMap<String, *>> {
        return reportsList
    }

    fun updateDatabase() {
        userRef.child("accountInfo").setValue(this)
    }

    override fun toString(): String {
        return "\n${currentUser?.displayName}\n ${this.income.toString()} \n ${this.expenditureList.toString()} \n ${this.expenditureTotal.toString()}"
    }
}
