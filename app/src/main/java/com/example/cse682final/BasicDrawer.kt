package com.example.cse682final

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess


open class BasicDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {


    // Grabs current user's account info
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var accountInfo = AccountInfo()
    // Set up other class vars
    private var navigationView: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var bottomNavigationView : BottomNavigationView? = null
    private var summaryFragment: SummaryFragment? = null
    private var expenditureFragment: ExpenditureFragment? = null
    private var reportFragment: ReportListFragment? = null
    private var settingsFragment: SettingsFragment? = null

    fun onCreateDrawer() {
        setContentView(R.layout.activity_main)
        val topToolbar = findViewById<Toolbar>(R.id.top_toolbar)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        val database = FirebaseDatabase.getInstance()
        val uref = database.getReference("Users").child(currentUser?.uid!!).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                val name:TextView = findViewById(R.id.header_name)
                name.text = p0.child("displayName").value.toString()
                val email:TextView = findViewById(R.id.header_email)
                email.text = p0.child("email").value.toString()
                val profilePic = findViewById<ImageView>(R.id.header_profile_pic)
                val account = p0.child("accountInfo").getValue(AccountInfo::class.java)!!
                this@BasicDrawer.accountInfo.income = account.income
                this@BasicDrawer.accountInfo.expenditureTotal = account.expenditureTotal
                this@BasicDrawer.accountInfo.expenditureList = account.expenditureList
                this@BasicDrawer.accountInfo.savingsList = account.savingsList
                this@BasicDrawer.accountInfo.autoReportsList = account.autoReportsList
                this@BasicDrawer.accountInfo.alerts = account.alerts
            }
        })
        navigationView = findViewById(R.id.navigationView)
        navigationView?.setNavigationItemSelectedListener(this)
        bottomNavigationView = findViewById(R.id.bottom_nav_bar)
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        drawerLayout = findViewById(R.id.drawer_main)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, topToolbar, R.string.ndopen, R.string.ndclose)
        drawerLayout?.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawable = resources.getDrawable(R.drawable.money_sign,null)
        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable: Drawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 50, 50, true))
        supportActionBar?.setHomeAsUpIndicator(newdrawable) //Attributed to FreePik on flaticon.com
        supportActionBar!!.title = "Summary"
        //Create the fragments
        summaryFragment = SummaryFragment()
        expenditureFragment = ExpenditureFragment(accountInfo)
        reportFragment = ReportListFragment()
        settingsFragment = SettingsFragment()
        Log.d("Check Account","${accountInfo.toString()}")
        loadFragment(R.id.fragment_container,summaryFragment!!, "summary")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.summary_button -> loadFragment(R.id.fragment_container,summaryFragment!!, "summary")

            R.id.exp_button -> loadFragment(R.id.fragment_container, expenditureFragment!!, "expenditure")

            R.id.report_button -> loadFragment(R.id.fragment_container,reportFragment!!, "report")

            R.id.settings -> {
                supportActionBar!!.title = "Settings"
                loadFragment(R.id.fragment_container, settingsFragment!!, "settings")
            }

        }
        drawerLayout!!.closeDrawers()
        return true
    }
    fun loadFragment(id : Int, f : Fragment, tag:String) {
        supportFragmentManager.beginTransaction().replace(id, f,tag).commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    return
                else
                    Toast.makeText(this,"This feature needs access to your location",Toast.LENGTH_LONG).show()
                exitProcess(0)
            }
        }
    }

}
