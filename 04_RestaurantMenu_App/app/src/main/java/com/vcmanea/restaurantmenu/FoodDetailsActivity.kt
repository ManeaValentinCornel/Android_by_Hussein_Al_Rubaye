package com.vcmanea.restaurantmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_details.*

class FoodDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)


        val bundle=intent.extras as Bundle

        details_image.setImageResource(bundle.getInt("img"))
        details_title.text=(bundle.getString("name"))
        details_description.text=(bundle.getString("des"))




    }
}
