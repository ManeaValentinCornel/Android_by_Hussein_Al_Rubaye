package com.vcmanea.zooapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)


        val b:Bundle?=intent.extras
        val name=b?.getString("name")
        val des=b?.getString("desc")
        val image:Int=b!!.getInt("image")

        infoImg.setImageResource(image)
        infoTItle.setText(name)
        infoDetails.setText(des)


    }
}
