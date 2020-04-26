package com.vcmanea.restaurantmenu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.food_ticket.view.*

class FoodAdapter(var list: ArrayList<Food>, var context: Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.food_ticket, parent, false)
        var food = list[position]
        view.imgFood.setImageResource(food.img)
        view.txtFood.setText(food.name)
        view.imgFood.setOnClickListener {
            val intent = Intent(context, FoodDetailsActivity::class.java)
            intent.putExtra("name", food.name)
            intent.putExtra("des", food.des)
            intent.putExtra("img", food.img)
            context.startActivity(intent)





        }

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}