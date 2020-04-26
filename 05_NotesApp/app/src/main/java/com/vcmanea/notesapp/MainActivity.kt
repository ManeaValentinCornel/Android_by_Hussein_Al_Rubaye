package com.vcmanea.notesapp

import android.app.SearchManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {



    private val notesList = ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"Create",Toast.LENGTH_SHORT).show()

    }

    override fun onResume() {
        loadQuery("%")
        super.onResume()
    }






    //QUERY
    fun loadQuery(title: String) {
        notesList.clear()
        val dbManager = DBManager(this)
        //if projection is null you need all the colums
        val projection = arrayOf(dbManager.colID, dbManager.colTitle, dbManager.colDescription)
        val selectionArgs = arrayOf(title)

        //SELECTION SELECT THE TITLE LIKE ? -> SELECTION ARGUMENTS, replacing ? with selectionArgs
        val cursor = dbManager.queryData(
            projection,
            "${dbManager.colTitle} LIKE ?",
            selectionArgs,
            dbManager.colTitle
        )
        if (cursor.moveToFirst()) {
            do {

                val ID = cursor.getInt(cursor.getColumnIndex(dbManager.colID))
                val Title = cursor.getString(cursor.getColumnIndex(dbManager.colTitle))
                val Description = cursor.getString(cursor.getColumnIndex(dbManager.colDescription))

                notesList.add(Notes(ID, Title, Description))
            } while (cursor.moveToNext())
        }
        val myAdapter = MyNotesAdapter(notesList, this)
        list_notes.adapter = myAdapter
    }


    //SEARCH and add menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.apply { searchManager.getSearchableInfo(componentName) }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                loadQuery("%$newText%")
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    //ON OPTION SELECTED
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //how android works ?
        when (item.itemId) {
            R.id.menu_add -> {
                val intent = Intent(this, AddNotes::class.java)
                startActivity(intent)


            }

        }

        return super.onOptionsItemSelected(item)
    }

    //ADAPTER
    inner class MyNotesAdapter(private var listNotes: ArrayList<Notes>, var context: Context) :
        BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val note = listNotes[position]
            val view = LayoutInflater.from(context).inflate(R.layout.ticket, parent, false)
            view.title.setText(note.noteName)
            view.des.setText(note.noteDes)
            view.delete.setOnClickListener {
                var dbManager = DBManager(context)
                dbManager.delete("ID=?", arrayOf(note.noteID.toString()))
                loadQuery("%")
            }

            view.update.setOnClickListener {
                var intent=Intent(context,AddNotes::class.java)
                intent.putExtra("id",note.noteID)
                intent.putExtra("title",note.noteName)
                intent.putExtra("description",note.noteDes)
                startActivity(intent)





            }




            return view
        }

        override fun getItem(position: Int): Any {
            return listNotes[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotes.size
        }
    }

}
