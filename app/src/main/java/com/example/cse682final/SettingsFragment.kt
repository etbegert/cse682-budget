package com.example.cis651_finalproject

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
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    private lateinit var v : View
    var searchRadius = 25
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.settings_fragment, container, false)
        // TODO determine if settings fragment is even necessary?
        val unitsMode = v.findViewById<Switch>(R.id.units)
        unitsMode.setOnCheckedChangeListener {buttonView, isChecked ->
            MapsFragment.units = isChecked
        }
        /*val search = v.findViewById<EditText>(R.id.search_radius)
        // TODO make sure it does not reset to 10 if it goes down to 0 (use different listener?)
        search.addTextChangedListener {
            val textCheck = search.text.toString().toIntOrNull()
            searchRadius = if(textCheck != null) {
                textCheck
            } else {
                Toast.makeText(context, "Search radius needs to be a valid whole number", Toast.LENGTH_SHORT).show()
                10
            }
        }*/
        v.findViewById<Button>(R.id.sign_out_button).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        return v
    }

    fun toggleDarkMode() {
        //TODO figure out dark mode switching functionality
    }
}