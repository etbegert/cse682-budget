package com.example.cse682final

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

    constructor(inc: Int) : this() {
        this.income = inc
    }

    constructor(inc: Int, expenditures: ArrayList<Float>, expendTotal: Float, savings: HashMap<String, Float>, autoReports: HashMap<String, Int>, alertsList: ArrayList<String>) : this() {
        this.income = inc
        this.expenditureList = expenditures
        this.expenditureTotal = expendTotal
        this.savingsList = savings
        this.autoReportsList = autoReports
        this.alerts = alertsList
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

    // Get the user's income
    fun getInc(): Int {
        return income
    }

    // Get a specific expenditure
    fun getExpenditure(position: Int): Float {
        return expenditureList.get(position)
    }

    fun getExpenditures(): ArrayList<Float> {
        return expenditureList
    }


    companion object {
        lateinit var reports: MutableList<MutableMap<String, *>>
    }

    // Get the user's alerts
    @JvmName("getAlerts1")
    fun getAlerts(): ArrayList<String> {
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
        return "${currentUser?.displayName}\n ${this.income.toString()} \n ${this.expenditureList.toString()} \n ${this.expenditureTotal.toString()}"
    }
}
