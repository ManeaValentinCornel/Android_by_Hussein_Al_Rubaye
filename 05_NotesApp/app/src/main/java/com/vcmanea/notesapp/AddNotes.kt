package com.vcmanea.notesapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.lang.Exception


var id = 0;

class AddNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)



        try {
            var bundle: Bundle? = intent.extras
            id = bundle!!.getInt("id", 0)
            add_title.setText(bundle?.getString("title"))
            add_des.setText(bundle?.getString("description"))
        } catch (ex: Exception) {
            ex.message
        }


    }

    fun buAdd(view: View) {
        var dbManager = DBManager(this)
        var values = ContentValues()

        values.put(dbManager.colTitle, add_title.text.toString())
        values.put(dbManager.colDescription, add_des.text.toString())


        //if id is greater than 0 the dat was added succesfully to the table otherwise not


        if (id != 0) {

            val ID = dbManager.update(values, "${dbManager.colID}=?", arrayOf(id.toString()))
            if (ID > 0) {
                Toast.makeText(this, "The note was succesfully edited", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        } else {

            var ID = dbManager.insertData(values)
            if (ID > 0) {
                Toast.makeText(this, "The note was succesfully added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
