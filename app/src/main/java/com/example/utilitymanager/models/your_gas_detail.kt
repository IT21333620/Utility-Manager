package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.OtherUti
import com.example.utilitymanager.R

class your_gas_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_gas_detail)

        val buttonutton = findViewById<Button>(R.id.button24)
        val buttonutton2 = findViewById<Button>(R.id.btnWeek2)



        buttonutton.setOnClickListener {
            var intent = Intent(this@your_gas_detail, OtherUti::class.java)
            startActivity(intent)
        }

        buttonutton2.setOnClickListener {
            var intent = Intent(this@your_gas_detail, available_hrs_in_gas::class.java)
            startActivity(intent)
        }



    }
}