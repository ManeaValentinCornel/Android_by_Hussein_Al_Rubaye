package com.vcmanea.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboo", "Babon lives in big place with tree", R.drawable.baboon))
        listOfAnimals.add(Animal("Buldog", "Buldog lives in big place with tree", R.drawable.bulldog,true))
        listOfAnimals.add(Animal("Panda", "Panda lives in big place with tree", R.drawable.panda))
        listOfAnimals.add(Animal("Sparrow", "Sparrow lives in big place with tree", R.drawable.sparrow_bird,true))
        listOfAnimals.add(Animal("Tiger", "Tiger lives in big place with tree", R.drawable.white_tiger,true))
        listOfAnimals.add(Animal("Zebra", "Zebra lives in big place with tree", R.drawable.zebra))

        val myAdapter:AnimalAdapter= AnimalAdapter(listOfAnimals,this)
        lvAnimal.adapter=myAdapter

    }


    class AnimalAdapter : BaseAdapter {

        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null

        constructor(listOfAnimals: ArrayList<Animal>, context: Context) : super() {
            this.context = context
            this.listOfAnimals = listOfAnimals
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]


            var myView = LayoutInflater.from(context).inflate(R.layout.animal_ticket, null)
            if (animal.isCarnivor){
                myView.linear_ticket.setBackgroundResource(R.drawable.background_carnivor)

            }
            myView.imgAnimal.setImageResource(animal.image)
            myView.twTitle.setText(animal.name)
            myView.twDescription.setText(animal.description)
            myView.setOnClickListener{
                val intent= Intent(context,AnimalInfoActivity::class.java)
                intent.putExtra("name",animal.name!!)
                intent.putExtra("des",animal.description)
                intent.putExtra("image",animal.image)
                context!!.startActivity(intent)

            }
            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }


    }


}
