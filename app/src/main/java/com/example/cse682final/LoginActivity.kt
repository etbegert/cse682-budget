package com.example.cis651_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity(){
    private lateinit var mAuth : FirebaseAuth
    private var currentUser : FirebaseUser? = null
    private lateinit var signUpButton : Button
    private var usersRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        mAuth = FirebaseAuth.getInstance()
        usersRef = FirebaseDatabase.getInstance().getReference("Users")
        currentUser = mAuth.currentUser
        Log.d("Debug current user", currentUser.toString())
        //Signup button onClick
        signUpButton = findViewById(R.id.sign_up_button)
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        // Reset Button onClick
        findViewById<Button>(R.id.reset_pass_button).setOnClickListener {
            if(email.text.toString() == "")
            {
                Toast.makeText(this, "Please enter your email above.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.sendPasswordResetEmail(email.text.toString()).addOnFailureListener(this) {
                Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener(this) {
                Toast.makeText(this@LoginActivity, "If associated with an account, password reset email was sent to ${email.text}.", Toast.LENGTH_SHORT).show()
            }
        }
        //Login onClick
        findViewById<Button>(R.id.sign_in_button).setOnClickListener {
            if(email.text.toString() == "" || password.text.toString() == "") {
                Toast.makeText(this, "Please provide all login information.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnSuccessListener(this) {
                currentUser = it.user
                if(currentUser?.isEmailVerified!!) {
                    Toast.makeText(this@LoginActivity, "Login successful.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                else Toast.makeText(this@LoginActivity, "Please verify your email and attempt to login again.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener(this) {
                Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun resetPassword(view: View) {
        val email = findViewById<EditText>(R.id.email).text.toString()
        //TODO check whether they have an account before sending email
        mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
            Toast.makeText(this, "Password reset email sent to $email if that is a valid account.", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
        }

    }

    /*private fun updateUI() {
        if(currentUser != null) {
            findViewById<TextInputLayout>(R.id.display_name_layout).visibility = View.GONE
            signUpButton.visibility = View.GONE
        }
    }*/
}