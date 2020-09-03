package com.example.cse682final

class AccountInfo(inc: Int) {
    var income = inc
    var expenditureList = ArrayList<Float>()
    var expenditureTotal : Float = 0F
    var savingsList = HashMap<String, Float>()
    var autoReportsList = HashMap<String, Int>()
    var alerts = ArrayList<String>()

    fun AccountInfo(inc: Int, expenditures: ArrayList<Float>, expendTotal: Float, savings: HashMap<String, Float>, autoReports: HashMap<String, Int>, alertsList: ArrayList<String>) {
        this.income = inc
        this.expenditureList = expenditures
        this.expenditureTotal = expendTotal
        this.savingsList = savings
        this.autoReportsList = autoReports
        this.alerts = alertsList
    }

    // Set the user's income
    fun accountInfo(userIncome: Int) {
        this.income = userIncome
    }

    // Add the user's expenditure
    fun addExpenditure(expend: Float) {
        this.expenditureList.add(expend)
        this.expenditureTotal += expend

    }

    // Add the user's savings
    fun addSavings(savingName: String, saving: Float) {
        this.savingsList.put(savingName, saving)
    }

    // Add the user's alerts
    @JvmName("setAlerts1")
    fun setAlerts(alertsList: ArrayList<String>) {
        this.alerts = alertsList
    }

    // Add the user's auto reports
    fun setAutoReports(autoReports: HashMap<String, Int>) {
        this.autoReportsList = autoReports
    }

    // Get the user's income
    @JvmName("getIncome1")
    fun getIncome(): Int {
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
}
