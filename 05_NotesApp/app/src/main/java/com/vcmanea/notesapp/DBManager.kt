package com.vcmanea.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DBManager {
    //VARIABLES
    var sqlDB: SQLiteDatabase? = null
    var context: Context? = null
    val dbName = "MyNotes"
    val dbTable = "Notes"
    val colID = "ID"
    val colTitle = "Name"
    val colDescription = "Description"
    val dbVersion = 1
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable (" +
            "$colID INTEGER PRIMARY KEY NOT NULL, " +
            "$colTitle TEXT, " +
            "$colDescription TEXT)"
    val sqlDropTable = "DROP TABLE IF EXISTS $dbTable"

    //SECONDARY CONSTRUCTOR
    constructor(context: Context) {
        val db = DatabaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    //INSERT
    fun insertData(values: ContentValues): Long {
        val ID = sqlDB!!.insert(dbTable, "", values)
        return ID

    }

    //DELETE
    fun delete(whereClauses: String, whereArgs: Array<String>): Int {
        val count = sqlDB!!.delete(dbTable, whereClauses, whereArgs)
        return count
    }

    //QUERY
    fun queryData(projection: Array<String>?, selection: String, selectionArgs: Array<String>, sortOrder: String): Cursor {
        val queryDB = SQLiteQueryBuilder()
        queryDB.tables = dbTable
        val cursor =
            queryDB.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder)
        return cursor
    }

    fun update(values:ContentValues,whereClauses:String,whereArgs:Array<String>):Int{

        val count=sqlDB!!.update(dbTable,values,whereClauses,whereArgs)

        return count;
    }





    //DATABASE HELPER
    inner class DatabaseHelperNotes : SQLiteOpenHelper {
        //SQLITE HELPER IF THE DATABASE IS NTO AVAILABLE WILL CREATE A DATABSE FOR YOU
        //CREATE THE DATABASE ONLY IF IT IS NOT AVAILABLE
        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(sqlCreateTable)
            Toast.makeText(context, "dataBase is created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(sqlDropTable)
        }

    }


}