package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R

class remaining_fuel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_fuel)

        val viewHistory = findViewById<Button>(R.id.button22)

        viewHistory.setOnClickListener {
            startActivity(Intent(this,fuel_usage_history::class.java))
        }
    }
}