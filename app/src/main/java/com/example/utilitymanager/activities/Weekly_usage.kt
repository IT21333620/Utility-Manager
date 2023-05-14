package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.R
import android.widget.Toast

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
        if (totalWaterUnit in 0.0..5.0) {
            weeklyCost = totalWaterUnit*8.0
        } else if (totalWaterUnit in 6.0..10.0) {
            weeklyCost = totalWaterUnit*11.0
        } else if (totalWaterUnit in 11.0..15.0) {
            weeklyCost = totalWaterUnit*20.0
        } else if (totalWaterUnit in 16.0..20.0) {
            weeklyCost = totalWaterUnit*40.0
        } else if (totalWaterUnit in 21.0..25.0) {
            weeklyCost = totalWaterUnit*58.0
        } else if (totalWaterUnit in 26.0..30.0) {
            weeklyCost = totalWaterUnit*88.0
        } else if (totalWaterUnit in 31.0..40.0) {
            weeklyCost = totalWaterUnit*105.0
        } else if (totalWaterUnit in 41.0..50.0) {
            weeklyCost = totalWaterUnit*120.0
        } else if (totalWaterUnit in 51.0..75.0) {
            weeklyCost = totalWaterUnit*130.0
        } else if (totalWaterUnit > 75.0) {
            weeklyCost = totalWaterUnit*140.0
        }

        resultTextView3.text = "Weekly Usage Cost for week: ${weeklyCost} Rs./Units"

        if (totalWaterConsumed > 6300) {
            val message = "You are exceeding estimated average household water consumption for the week"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }



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
