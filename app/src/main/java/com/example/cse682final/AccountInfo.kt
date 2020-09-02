package com.example.cse682final

class AccountInfo {
    var income = 0
    var expenditureList = ArrayList<Float>()
    var savingsList = HashMap<String, Float>()
    var autoReportsList = HashMap<String, Int>()
    var alerts = ArrayList<String>()

    // Set the user's income
    public fun accountInfo(userIncome: Int) {
        this.income = userIncome
    }

    // Add the user's expenditure
    public fun addExpenditure(expend: Float){
        this.expenditureList.add(expend)
    }

    // Add the user's savings
    public fun addSavings (savingName: String, saving: Float){
        this.savingsList.put(savingName, saving)
    }

    // Add the user's alerts
    @JvmName("setAlerts1")
    public fun setAlerts(alertsList: ArrayList<String>) {
        this.alerts = alertsList
    }

    // Add the user's auto reports
    public fun setAutoReports(autoReports: HashMap<String, Int>) {
        this.autoReportsList = autoReports
    }

    // Get the user's income
    @JvmName("getIncome1")
    public fun getIncome(): Int {
        return income
    }

    // Get the user's expenditures
    public fun getExpenditure(): ArrayList<Float> {
        return expenditureList
    }

    // Get the user's savings
    public fun getSavings(): HashMap<String, Float> {
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