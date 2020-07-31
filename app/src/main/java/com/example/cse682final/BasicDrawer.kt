package com.example.cis651_finalproject

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.cse682final.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess

open class BasicDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private var navigationView: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var bottomNavigationView : BottomNavigationView? = null
    private val currentUser = FirebaseAuth.getInstance().currentUser

    //private val localFragment = LocalFragment()

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
        // TODO update icon supportActionBar?.setHomeAsUpIndicator(R.drawable.martini_glass) //Attributed to FreePik on flaticon.com
        supportActionBar!!.title = "Summary"
        //TODO update fragment load loadFragment(R.id.fragment_container, localFragment, "local")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        /*TODO update fragment navigation when (item.itemId) {
            R.id.local_button -> {
                supportActionBar!!.title = "Local Area Feed"
                loadFragment(R.id.fragment_container, localFragment, "local")
            }
            R.id.map_button -> {
                supportActionBar!!.title = "Local Area Map"
                loadFragment(R.id.fragment_container, mapsFragment, "maps")
            }
            R.id.search_button -> {
                supportActionBar!!.title = "Search"
                loadFragment(R.id.fragment_container, searchFragment, "search")
            }
            R.id.profile -> {
                supportActionBar!!.title = "Profile"
                val bundle = Bundle()
                bundle.putString("uid", currentUser!!.uid)
                profileFragment.arguments = bundle
                loadFragment(R.id.fragment_container, profileFragment, "profile")
            }

            R.id.settings -> {
                supportActionBar!!.title = "Settings"
                loadFragment(R.id.fragment_container, settingsFragment, "settings")
            }

            R.id.about_app -> {
                supportActionBar!!.title = "About"
                loadFragment(R.id.fragment_container, aboutFragment,"about")
            }
        }*/
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
    companion object {
        public fun checkLocationPermissions(activity:Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), 1
            )

        }
    }
}
