package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.R

class monthly_water_bill : AppCompatActivity() {

    private lateinit var resultTextView1: TextView
    private lateinit var resultTextView2: TextView
    private lateinit var resultTextView3: TextView
//    private lateinit var resultTextView4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_water_bill)


        resultTextView1 = findViewById(R.id.textView113)
        resultTextView2 = findViewById(R.id.textView106)
        resultTextView3 = findViewById(R.id.textView107)
//        resultTextView4 = findViewById(R.id.textView22)

        val totalWaterConsumedWeek1 = intent.getDoubleExtra("TOTAL_WATER_CONSUMED_WEEK1", 0.0)
        val totalWaterConsumedWeek2 = intent.getDoubleExtra("TOTAL_WATER_CONSUMED_WEEK2", 0.0)
        val totalWaterConsumedWeek3 = intent.getDoubleExtra("TOTAL_WATER_CONSUMED_WEEK3", 0.0)
        val totalWaterConsumedWeek4 = intent.getDoubleExtra("TOTAL_WATER_CONSUMED_WEEK4", 0.0)

        val totalWaterConsumed = totalWaterConsumedWeek1 + totalWaterConsumedWeek2 + totalWaterConsumedWeek3 + totalWaterConsumedWeek4
        resultTextView1.text = "Total water consumed for month: ${totalWaterConsumed} liters"

        val totalWaterUnitsWeek1 = totalWaterConsumedWeek1/1000.0
        val totalWaterUnitsWeek2 = totalWaterConsumedWeek2/1000.0
        val totalWaterUnitsWeek3 = totalWaterConsumedWeek3/1000.0
        val totalWaterUnitsWeek4 = totalWaterConsumedWeek4/1000.0

        val totalWaterUnits = totalWaterUnitsWeek1 + totalWaterUnitsWeek2 + totalWaterUnitsWeek3 + totalWaterUnitsWeek4
        resultTextView2.text = "Total water units for month:${totalWaterUnits} units"


        var  monthlyCost = 0.0
        if(totalWaterUnits > 0) {
            monthlyCost = (totalWaterUnits*24+300)
        }
        resultTextView3.text = "Weekly Cost for week:${ monthlyCost} LKR"




        val ButtonOpen : Button = findViewById(R.id.button27)
        ButtonOpen.setOnClickListener{

            startActivity(Intent( this, weekly_usage::class.java))

        }
    }
}