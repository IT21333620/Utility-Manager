package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.OtherUti
import com.example.utilitymanager.R

class Networkmain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networkmain)


        val buttonutton2 = findViewById<Button>(R.id.button4)

        buttonutton2.setOnClickListener {
            var intent = Intent(this@Networkmain, availabledataamount::class.java)
            startActivity(intent)
        }


    }
}