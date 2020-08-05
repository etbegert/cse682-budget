package com.example.cse682final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class SplashScreen : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()
        mAuth = FirebaseAuth.getInstance()
        currentUser = mAuth!!.currentUser
        val countDown = object: CountDownTimer(3000, 1000) {

            override fun onFinish() {
                if(currentUser == null) {
                    Toast.makeText(this@SplashScreen, "No user found.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                    finish()
                }
                else {
                    if (currentUser?.isEmailVerified!!) {
                        Toast.makeText(
                            this@SplashScreen,
                            "User already logged in.",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@SplashScreen,
                            "Please verify email and log in.",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                        finish()
                    }
                }
            }
            override fun onTick(millisUntilFinished: Long) {
                //no functionality
            }
        }.start()
    }
}