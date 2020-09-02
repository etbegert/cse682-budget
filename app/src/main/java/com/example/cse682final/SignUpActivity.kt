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

class SignUpActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    private var usersRef: DatabaseReference? = null
    private var mAuth : FirebaseAuth? = null
    private var currentUser : FirebaseUser? = null
    private var storage: FirebaseStorage? = null
    private var imageUID: String? = null
    private var uri: Uri? = null
    public var income: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        storage = FirebaseStorage.getInstance()
        mAuth = FirebaseAuth.getInstance()
        usersRef = FirebaseDatabase.getInstance().getReference("Users")
        currentUser = mAuth?.currentUser
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
                    imageUID = UUID.randomUUID().toString()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == HelperFunctions.REQUEST_FOR_CAMERA && resultCode == RESULT_OK) {
            Log.d("DB URI Signup (Camera)", uri.toString())
            return
        }
        else if(requestCode == HelperFunctions.OPEN_FILE && resultCode == RESULT_OK) {
            uri = data?.data!!
            Log.d("DB URI Signup (Storage)", uri.toString())
            return
        }
        else {
            Toast.makeText(this, "Error occurred while getting photo from camera or storage", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserDataToDB() {
        if(uri == null) uri = Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${resources.getResourcePackageName(R.drawable.basic_avatar)}/${resources.getResourceTypeName(R.drawable.basic_avatar)}/${resources.getResourceEntryName(R.drawable.basic_avatar)}")
        if(imageUID != null) {
            val checks = ArrayList<Boolean>()
            checks.add(findViewById<CheckBox>(R.id.signup_interest_bars).isChecked)
            checks.add(findViewById<CheckBox>(R.id.signup_interest_clubs).isChecked)
            checks.add(findViewById<CheckBox>(R.id.signup_interest_restaurants).isChecked)
            checks.add(findViewById<CheckBox>(R.id.signup_interest_diners).isChecked)
            checks.add(findViewById<CheckBox>(R.id.signup_interest_driveins).isChecked)
            checks.add(findViewById<CheckBox>(R.id.signup_interest_gastropubs).isChecked)
            usersRef?.child(currentUser?.uid!!)?.setValue(
                User(
                    display_name_signup.text.toString(),
                    email_signup.text.toString(),
                    imageUID!!,
                    uri!!,
                    currentUser?.uid!!.toString(),
                    checks,
                    income
                )
            )
            HelperFunctions.uploadImage("profile_pictures", imageUID, uri)
        }
        else
            Toast.makeText(this, "No image UID was generated.", Toast.LENGTH_SHORT).show()
    }

    private fun takePhoto() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Your Camera")
        uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        val chooser = Intent.createChooser(intent, "Select a Camera App.")
        if(intent.resolveActivity(packageManager) != null) {
            startActivityForResult(chooser, HelperFunctions.REQUEST_FOR_CAMERA)
        }
    }

    private fun checkForPermissions() {
        if(ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "The application needs permission to access your camera and photo.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                HelperFunctions.REQUEST_FOR_CAMERA
            )
        }
        else {
            takePhoto()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == HelperFunctions.REQUEST_FOR_CAMERA) {
            if(ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                takePhoto()
            }
        }
        else Toast.makeText(this, "The application needs permission to access your camera and photo.", Toast.LENGTH_SHORT).show()
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun uploadProfilePhoto(view: View) {
        val popup = PopupMenu(this, view)
        menuInflater.inflate(R.menu.camera_menu, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.take_photo -> {
                checkForPermissions()
                true
            }
            R.id.upload_photo -> {
                val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(
                    Intent.createChooser(intent, "Select a file"),
                    HelperFunctions.OPEN_FILE
                )
                true
            }
            else -> {
                false
            }
        }
    }
}