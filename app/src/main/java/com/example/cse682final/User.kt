package com.example.cse682final

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class User(var displayName: String, var email: String, var profilePhotoUID: String, imageURI: Uri, var uid: String, var interests: ArrayList<Boolean>) {
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    var phone: String = "0000000000"
    init {
        Log.d("Debugging photo UID", profilePhotoUID)
        HelperFunctions.uploadImage("profile_pictures", profilePhotoUID, imageURI)
    }
}