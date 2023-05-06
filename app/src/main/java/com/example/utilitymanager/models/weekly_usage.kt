package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.R
import com.example.utilitymanager.water_historylist

class weekly_usage : AppCompatActivity() {

    private lateinit var resultTextView1: TextView
    private lateinit var resultTextView2: TextView
    private lateinit var resultTextView3: TextView


    private lateinit var btnFetchData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_usage)

        resultTextView1 = findViewById(R.id.textView108)
        resultTextView2 = findViewById(R.id.textView20)
        resultTextView3 = findViewById(R.id.textView21)

        val totalWaterConsumed = intent.getDoubleExtra("TOTAL_WATER_CONSUMED", 0.0)
        resultTextView1.text = "Total water consumed for week: ${totalWaterConsumed} liters"

        val totalWaterUnit = totalWaterConsumed/1000.0
        resultTextView2.text = "Total water units for week:${totalWaterUnit} units"

        var weeklyCost = 0.0
        if(totalWaterUnit > 0) {
            weeklyCost = (totalWaterUnit*24+300)
        }
        resultTextView3.text = "Weekly Cost for week:${weeklyCost} LKR"



        val ButtonOpen : Button = findViewById(R.id.button26)
        ButtonOpen.setOnClickListener{

            startActivity(Intent( this, user_information::class.java))

        }

        val button23 = findViewById<Button>(R.id.button23)
        button23.setOnClickListener{
            val intent = Intent(this, water_historylist::class.java)
            startActivity(intent)
        }


//        val myButton = findViewById<Button>(R.id.button6)
//        myButton.setOnClickListener {
//            // code to be executed when the button is clicked
//            // e.g. start a new activity
//            val intent = Intent(this, monthly_water_bill::class.java)
//            startActivity(intent)
//        }

//        val myButton2 = findViewById<Button>(R.id.button23)
//        myButton2.setOnClickListener {
//            // code to be executed when the button is clicked
//            // e.g. start a new activity
//            val intent = Intent(this, weekly_history::class.java)
//            startActivity(intent)
//        }



    }
}
