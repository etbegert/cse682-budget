package com.example.cse682final

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage

// Class to hold relevant values for a user of the budgeting app, used to upload the data as a single object to FirebaseDatabase
class User(var displayName: String, var email: String, var uid: String, var accountInfo: AccountInfo) {}