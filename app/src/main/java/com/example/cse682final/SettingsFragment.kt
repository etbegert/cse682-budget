package com.example.cse682final

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.cse682final.R
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.settings_fragment.*

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment(val accountInfo: AccountInfo) : Fragment() {

    private lateinit var v : View
    private lateinit var incomeEntry : EditText
    private lateinit var billsEntry : EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.settings_fragment, container, false)
        v.findViewById<Button>(R.id.sign_out_button).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        incomeEntry = v.findViewById(R.id.income)
        billsEntry = v.findViewById(R.id.bills)
        incomeEntry.setHint("$" + String.format("%,.2f",accountInfo.income.toString().toFloat()))
        billsEntry.setHint("$" + String.format("%,.2f",accountInfo.billsTotal.toString().toFloat()))


        return v
    }
    /*Update bills and income when leaving the screen*/
    override fun onStop() {
        super.onStop()
        if(billsEntry.text.toString() != "")
            accountInfo.billsTotal = billsEntry.text.toString().replace("[^\\d.]", "").toFloat()
        if(incomeEntry.text.toString() != "")
            accountInfo.income = incomeEntry.text.toString().replace("[^\\d.]", "").toFloat()
        accountInfo.updateDatabase()
    }



}