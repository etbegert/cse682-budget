package com.example.cse682final

class AccountInfo {
    var income = 0
    var expenditureList = ArrayList<Int>()
    var savingsList = HashMap<String, Int>()
    var autoReportsList = HashMap<String, Int>()
    var alerts = ArrayList<String>()

    // Set the user's income
    public fun accountInfo(userIncome: Int) {
        this.income = userIncome
    }

    // Add the user's expenditure
    public fun addExpenditure(expendList: ArrayList<Int>){
        this.expenditureList = expendList
    }

    // Add the user's savings
    public fun addSavings (saveList: HashMap<String, Int>){
        this.savingsList = saveList
    }

    // Add the user's alerts
    public fun addAlerts(alertsList: ArrayList<String>) {
        this.alerts = alertsList
    }

    // Add the user's auto reports
    public fun addAutoReports(autoReports: HashMap<String, Int>) {
        this.autoReportsList = autoReports
    }

    // Get the user's income
    @JvmName("getIncome1")
    public fun getIncome(): Int {
        return income
    }

    // Get the user's expenditures
    public fun getExpenditure(): ArrayList<Int> {
        return expenditureList
    }

    // Get the user's savings
    public fun getSavings(): HashMap<String, Int> {
        return savingsList
    }

    // Get the user's alerts
    @JvmName("getAlerts1")
    public fun getAlerts(): ArrayList<String> {
        return alerts
    }

    // Get the user's auto reports
    public fun getAutoReports(): HashMap<String, Int> {
        return autoReportsList
    }
}