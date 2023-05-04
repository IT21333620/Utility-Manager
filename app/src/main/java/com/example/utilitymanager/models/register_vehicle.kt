package com.example.utilitymanager.models

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.utilitymanager.fuel_fragment


class register_vehicle : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.utilitymanager.R.layout.activity_register_vehicle)
            val button = findViewById<Button>(com.example.utilitymanager.R.id.button4)
        button.setOnClickListener {
            startActivity(Intent(this, weeklyfuel_usage::class.java))
        }

//        val button1 = findViewById<Button>(com.example.utilitymanager.R.id.floatingActionButton22)
//
//        button1.setOnClickListener {
//            startActivity(Intent(this,fuel_fragment::class.java))
//        }


    }


}