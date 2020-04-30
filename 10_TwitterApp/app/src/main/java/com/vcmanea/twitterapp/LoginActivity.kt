package com.vcmanea.twitterapp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {
    private val READ_IMAGE: Int = 155
    private val PICK_IMAGE_CODE = 160
    //FIREBASE
    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var databaseRef = database.reference
    private var mDowloadURL: String? = null
    //ON CREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            loginToFirebase(eTxtEmail.text.toString(), eTxtPassword.text.toString())
        }
        imgPerson.setOnClickListener {
            checkReadExtenalStoragePermission()
        }
    }
    //ON START
    override fun onStart() {
        super.onStart()
        loadMain()
    }
    //SAVE IMAGE TO FIREBASE STORAGE
    fun saveImageInFirebase() {
        val currentUser = mAuth?.currentUser
        //can also use the reference by URl -> FirebaseStorage.getInstance().getReferenceFromUrl()
        val firebaseStorage = FirebaseStorage.getInstance()
        val firebaseStorageRef = firebaseStorage.reference
        //creating the image path using the first character of the user email address and dateformat
        val dateFormat = SimpleDateFormat("ddMMyyHHmmss", Locale.getDefault())
        val dateObj = Date()
        val imagePath = currentUser?.email?.substring(0, 4) + dateFormat.format(dateObj) + ".jpg"
        //Firebase image reference
        val imageRef = firebaseStorageRef.child("images/$imagePath")
        //Saving the image from the imgView to bitmap
        val drawable = imgPerson.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        //bitmaps and scaling them down before saving them into memory
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        //Upload picture to Storage
        imageRef.putBytes(data).addOnSuccessListener {
            //getting the image url location
            val downloadUrl = it.metadata?.reference?.downloadUrl
            downloadUrl?.addOnSuccessListener {
                var goodUrl = it.toString()
                databaseRef.child("Users").child(currentUser?.uid.toString()).child("imgUrl")
                    .setValue(goodUrl)
            }
            Toast.makeText(applicationContext, "Image saved on ", Toast.LENGTH_LONG).show()
            //save the image url location to RealTime Database
            databaseRef.child("Users").child(currentUser?.uid.toString()).child("email")
                .setValue(currentUser?.email.toString())

            loadMain()
        }.addOnFailureListener() {
            Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
        }

    }
    //CHECK PERMISSION
    fun checkReadExtenalStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READ_IMAGE)
                return
            }
        }
        loadImage()
    }
    //LOAD IMAGE ----------------------------------> FROM YOUR INTERNAL STORAGE USING AN INTENT
    fun loadImage() {
        val galleryIntent = Intent()
        galleryIntent.type = ("image/*")
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(galleryIntent, PICK_IMAGE_CODE)
    }
    //REQUESTING PERMISSION ---->           IF THE PACKAGE MANAGER PERMISSION IS NOT GRANTED
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            READ_IMAGE -> {
                //only one permission inside the request permission array
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
                else {
                    Toast.makeText(this, "Cannot access your images", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    //ON ACTIVITY RESULT
    //THIS METHOD GETS FIRED AUTOMATICALLY WHEN THE startActivityForResult is called
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE && resultCode == RESULT_OK && data != null) {

            try {
                val imgUri = data.data
                val bitMap: Bitmap =
                    BitmapFactory.decodeStream(imgUri?.let { contentResolver.openInputStream(it) })
                imgPerson.setImageBitmap(bitMap)
            } catch (ex: Exception) {
                ex.message
            }
        }
    }
    //LOGIN
    private fun loginToFirebase(email: String, password: String) {
        mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(applicationContext, "Successfully  login", Toast.LENGTH_LONG).show()
                saveImageInFirebase()
            }
        }
            ?.addOnFailureListener {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
            }
    }
    //LOAD MAIN
    private fun loadMain() {
        val currentUser = mAuth?.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
        }
    }


}
