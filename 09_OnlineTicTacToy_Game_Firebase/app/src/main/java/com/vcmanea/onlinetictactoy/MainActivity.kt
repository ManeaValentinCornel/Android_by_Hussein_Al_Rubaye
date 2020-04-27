package com.vcmanea.onlinetictactoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    //database instance
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    var myEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        var b: Bundle? = intent.extras

        myEmail = b?.getString("email")
        tvPlayerName.setText(myEmail.toString())



        btnAccept.setOnClickListener {
            accept()
        }
        btnRequest.setOnClickListener {
            request()

        }


        incommingCalls()


    }


    fun btnClick(view: View) {
        val btnSelected = view as Button
        var cellId = 0

        when (btnSelected.id) {
            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9

        }

        Log.d("buClick", btnSelected.id.toString())
        Log.d("buClick", cellId.toString())
        playGame(cellId, btnSelected)
        checkWinner()

    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellId: Int, btnSelected: Button) {
        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.blackGreen)
            activePlayer = 1
            player2.add(cellId)
        }

        btnSelected.isEnabled = false

    }

    fun checkWinner() {
        var winner = -1
        //ROW 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //ROW 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //ROW 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }


        //COLUM 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //COLUM 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //COLUM 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //DIAGONAL1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //DIAGANONAL
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner == 1) {
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            restart()
        } else if (winner == 2) {
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            restart()
        }

    }


    fun autoPlay() {

        val emptyCells = ArrayList<Int>()

        for (cellId in 1..9) {
            if (!player1.contains(cellId) && !player2.contains(cellId)) {
                emptyCells.add(cellId)
            }
        }
        if (emptyCells.size == 0) {
            restart()
        } else {

            val r = Random()
            val randomIndex = r.nextInt(emptyCells.size)
            val cellId = emptyCells[randomIndex]
            val btnSelected: Button?
            btnSelected = when (cellId) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                    btn1
                }
            }
            playGame(cellId, btnSelected)
        }
    }

    fun restart() {
        activePlayer = 1
        player1.clear()
        player2.clear()
        for (cellId in 1..9) {
            var btnSelected: Button = when (cellId) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                    btn1
                }
            }
            btnSelected.text = ""
            btnSelected.setBackgroundResource(R.color.whiteBtn)
            btnSelected.isEnabled = true

        }
    }

    //REQUEST INVITATION
    private fun request() {
        val userEmail = etEmail.text
        myRef.child("Users").child(userEmail.toString()).child("Request").push()
            .setValue(myEmail.toString())

    }

    //ACCEPT INVITATION
    private fun accept() {
        val userEmail = etEmail.text.toString()
        myRef.child("Users").child(userEmail).child("Request").push()
            .setValue(myEmail.toString())
    }


    //LISTEN INCOMING CALL
    fun incommingCalls() {
        myRef.child("Users").child(myEmail.toString()).child("Request")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        val td = dataSnapshot.value as HashMap<*, *>
                        val value: String
                        for (key in td.keys) {
                            value = td[key] as String
                            etEmail.setText(value.toString())
                            myRef.child("Users").child(myEmail.toString())
                                .child("Request").setValue(true)
                            break
                        }
                    } catch (ex: Exception) {
                        Toast.makeText(applicationContext, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }


    fun playOnline(sesionId:String){

    }

}
