package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R

class availabledataamount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availabledataamount)

        val buttonutton2 = findViewById<Button>(R.id.button10)

        buttonutton2.setOnClickListener {
            var intent = Intent(this@availabledataamount, AllAppScreen::class.java)
            startActivity(intent)
        }


    }
}