package com.example.cse682final

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage

class User(var displayName: String, var email: String, var profilePhotoUID: String, imageURI:  Uri, var uid: String, var interests: ArrayList<Boolean>, var income: String?) {
        private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    }