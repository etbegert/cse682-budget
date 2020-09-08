package com.example.cse682final

import android.accounts.Account
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : BasicDrawer(), ReportListFragment.OnItemSelectedListener{

    companion object{
        lateinit var mContext: Context
        lateinit var fragmentManager: FragmentManager
    }
    val REQUEST_CHECK_SETTINGS = 0x1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(R.layout.activity_main)
        super.onCreateDrawer()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onListItemSelected(
        shardeView: View,
        income: String,
        date: String,
        type: String,
        line1: String,
        line2: String,
        line3: String
    ){
        val args = Bundle()
        args.putString("income", income)
        args.putString("date", date)
        args.putString("type", type)
        args.putString("line1", line1)
        args.putString("line2", line2)
        args.putString("line3", line3)
        val rf: Fragment = ReportFragment()
        rf.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, rf)
            .addToBackStack(null).commit()

    }

}