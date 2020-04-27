package com.vcmanea.onlinetictactoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mFirebaseAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mFirebaseAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            loginToFirebase(etName.text.toString(), etPassword.text.toString())
        }
    }

    private fun loginToFirebase(email: String, password: String) {
        mFirebaseAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Successfully login", Toast.LENGTH_LONG)
                        .show()
                    //save in database
                    //add the user uid as child to the user
                    //and also set value for this specifuic user
                    var currentUser: FirebaseUser? = mFirebaseAuth?.currentUser
                    if (currentUser != null) {

                        myRef.child("Users").child(splitString(currentUser.email.toString())).child("Request").setValue(true)
                        loadMain()
                    } else {
                        Toast.makeText(applicationContext, "Failed to login", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            ?.addOnFailureListener { task->
                Toast.makeText(applicationContext, "Failed to login ${task.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }


    override fun onStart() {
        super.onStart()
       loadMain()
    }

    private fun loadMain() {

        var currentUser: FirebaseUser? = mFirebaseAuth?.currentUser
        if (currentUser != null) {

            var intent = Intent(this, MainActivity::class.java)

            intent.putExtra("email", splitString(currentUser.email.toString()))
            intent.putExtra("uid", currentUser.uid)

            startActivity(intent)
        }
    }

    fun splitString(str: String): String {
        var split = str.split("@")
        return split[0]

    }


}
