package com.example.utilitymanager.models

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R

class available_hrs_in_gas : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_hrs_in_gas)


        val buttonutton = findViewById<Button>(R.id.loginSignIn)

        buttonutton.setOnClickListener {
            var intent = Intent(this@available_hrs_in_gas, Weekly_gas_usage::class.java)
            startActivity(intent)
        }

    }
}