package com.example.cse682final

import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.cse682final.R
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class HelperFunctions {
    companion object {
        const val REQUEST_FOR_CAMERA = 11
        const val OPEN_FILE = 12
        private var storage: FirebaseStorage = FirebaseStorage.getInstance()

        fun uploadImage(directory: String, id: String?, uri: Uri?) {
            val path = "$directory/$id.jpg"
            val imageRef = storage.getReference(path)
            uri?.let {
                imageRef.putFile(it).addOnSuccessListener {
                    imageRef.downloadUrl
                        .addOnSuccessListener { uri ->
                            Log.d("Image upload successful", uri.toString())
                        }.addOnFailureListener { e ->
                            Log.d("Debug Profile Image", e.message.toString())
                        }
                }.addOnFailureListener { e ->
                    Log.d("Debug image upload", e.message.toString())
                }
            }
        }

        fun getImage(directory: String, id: String, view: ImageView) {
            val path = "$directory/$id.jpg"
            if(id != "" && id != "null") {
                val imageRef = storage.getReference(path)
                Log.d("Good path check", path)
                imageRef.downloadUrl.addOnCompleteListener {
                    Picasso.get().load(it.result).rotate((90).toFloat()).placeholder(R.drawable.basic_avatar).error(R.drawable.pngwave).resize(800,1000).centerInside().into(view)
                }
            }
            else {
                Log.d("Bad path check", path)
            }
        }
    }
}