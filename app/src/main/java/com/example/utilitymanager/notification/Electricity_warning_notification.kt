package com.example.utilitymanager.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R
import com.example.utilitymanager.activities.electricity_history

class electricity_warning_notification : AppCompatActivity() {
    private lateinit var btnWarning: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricity_warning_notification)
        btnWarning = findViewById(R.id.btnWarning)

        btnWarning.setOnClickListener {
            var intent = Intent(this, electricity_history::class.java)
            startActivity(intent)
        }
    }
}