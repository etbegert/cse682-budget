package com.example.cse682final

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AccountInfo() {
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val userRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser?.uid!!)
    var income: Int
    var expenditureList: ArrayList<Float>
    var expenditureTotal : Float
    var savingsList: HashMap<String, Float>
    var autoReportsList = HashMap<String, Int>()
    var alerts: ArrayList<String>

    init {
        income = 0
        expenditureList = ArrayList<Float>()
        expenditureTotal = 0F
        savingsList = HashMap<String, Float>()
        alerts = ArrayList<String>()
    }

    constructor(income: Int) : this() {
        this.income = income
    }

    constructor(income: Int, expenditureList: ArrayList<Float>, expenditureTotal: Float, savingsList: HashMap<String, Float>, autoReportsList: HashMap<String, Int>, alerts: ArrayList<String>) : this() {
        this.income = income
        this.expenditureList = expenditureList
        this.expenditureTotal = expenditureTotal
        this.savingsList = savingsList
        this.autoReportsList = autoReportsList
        this.alerts = alerts
    }

    // Set the user's income
    fun changeIncome(userIncome: Int) {
        this.income = userIncome
        updateDatabase()
    }

    // Add the user's expenditure
    fun addExpenditure(expend: Float) {
        this.expenditureList.add(expend)
        this.expenditureTotal += expend
        updateDatabase()
    }

    // Add the user's savings
    fun addSavings(savingName: String, saving: Float) {
        this.savingsList.put(savingName, saving)
        updateDatabase()
    }

    // Add the user's alerts
    fun setAlert(alertsList: ArrayList<String>) {
        this.alerts = alertsList
        updateDatabase()
    }

    // Add the user's auto reports
    fun setAutoReports(autoReports: HashMap<String, Int>) {
        this.autoReportsList = autoReports
        updateDatabase()
    }

    // Get a specific expenditure
    fun getExpenditure(position: Int): Float {
        return expenditureList.get(position)
    }

    companion object {
        lateinit var reports: MutableList<MutableMap<String, *>>
    }

    // Get the user's alerts
    fun getAlertsFun(): ArrayList<String> {
        return alerts
    }

    // Get the user's auto reports
    fun getAutoReports(): HashMap<String, Int> {
        return autoReportsList
    }

    fun updateDatabase() {
        userRef.child("accountInfo").setValue(this)
    }

    override fun toString(): String {
        return "\n${currentUser?.displayName}\n ${this.income.toString()} \n ${this.expenditureList.toString()} \n ${this.expenditureTotal.toString()}"
    }
}
