package com.example.bitfit

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " +
                FOOD + " TEXT," +
                CALORIE + " INTEGER" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addFood(foodName : String, calorieCount : String ){
        val values = ContentValues()

        values.put(FOOD, foodName)
        values.put(CALORIE, calorieCount)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getFood(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    companion object{
        private val DATABASE_NAME = "BitFit"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "calorie_table"

        val ID = "id"
        val FOOD = "food name"
        val CALORIE = "calorie count"
    }
}
