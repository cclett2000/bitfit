package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listData = ArrayList<Model>()
        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)

        // input vars
        val addItem = findViewById<Button>(R.id.food_input)
        val foodET = findViewById<EditText>(R.id.food_ET)
        val calET = findViewById<EditText>(R.id.cal_ET)

        addItem.setOnClickListener{
            val db = DBHelper(this, null)
            val foodName = foodET.text.toString()
            val calCount = calET.text.toString()

            db.addFood(foodName, calCount)
            Toast.makeText(this, "$foodName & $calCount Added To Database", Toast.LENGTH_LONG).show()

            val cursor = db.getFood()
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    listData.add(
                        Model(
                            cursor.getString(0),
                            cursor.getInt(1)
                        )
                    )
                    val adapter = RVAdapter(listData)
                    rView.adapter = adapter
                }
            }


            foodET.text.clear()
            calET.text.clear()
        }
    }
}
