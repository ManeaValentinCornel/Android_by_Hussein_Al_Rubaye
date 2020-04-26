package com.vcmanea.firebasedemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        //Authentication
        mAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            //fire when click login
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            loginToFirebase(email, password)
        }
    }

    fun loginToFirebase(email: String, password: String) {
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Successsul login", Toast.LENGTH_LONG).show()
                    Log.d("Login:",mAuth?.currentUser.toString())
                } else {
                    Toast.makeText(applicationContext, "Successsul login", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser=mAuth!!.currentUser
        if(currentUser!=null){
            val intent= Intent(this,Control::class.java)
            startActivity(intent)
        }
    }

}
