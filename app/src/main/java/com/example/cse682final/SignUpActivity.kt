package com.example.cse682final

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private var usersRef: DatabaseReference? = null
    private var mAuth : FirebaseAuth? = null
    private var currentUser : FirebaseUser? = null
    private var storage: FirebaseStorage? = null
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        storage = FirebaseStorage.getInstance()
        mAuth = FirebaseAuth.getInstance()
        usersRef = FirebaseDatabase.getInstance().getReference("Users")
        currentUser = mAuth?.currentUser
        // Create new account when button is clicked and send email verification
        findViewById<Button>(R.id.create_account_button).setOnClickListener {
            if(email_signup.text.toString() == "" || password_signup.text.toString() == "") {
                Toast.makeText(this, "Please fill out all required information.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth?.createUserWithEmailAndPassword(email_signup.text.toString(), password_signup.text.toString())?.addOnSuccessListener(this
            ) {
                currentUser = it.user
                currentUser?.sendEmailVerification()?.addOnSuccessListener(this) {
                    Toast.makeText(this@SignUpActivity, "Signup successful! Verification email sent to ${email_signup.text}.", Toast.LENGTH_SHORT).show()
                    saveUserDataToDB()
                    finish()
                }?.addOnFailureListener(this@SignUpActivity) {
                    Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }?.addOnFailureListener(this@SignUpActivity) {
                Toast.makeText(this@SignUpActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.signup_cancel_button).setOnClickListener {
            finish()
        }
    }

    // Saves a User object with all relevant properties to FirebaseDatabase
    private fun saveUserDataToDB() {
        try {
            usersRef?.child(currentUser?.uid!!)?.setValue(
                User(
                    display_name_signup.text.toString(),
                    email_signup.text.toString(),
                    currentUser?.uid!!.toString(),
                    AccountInfo(income_amount_signup.text.toString().toFloat())
                )
            )
        }
        catch(exp: Exception) {
            Log.d("Database save", "Failed saving user info to database")
            Toast.makeText(this, "Failed saving user info to database, check income.", Toast.LENGTH_LONG).show()
        }
    }
}