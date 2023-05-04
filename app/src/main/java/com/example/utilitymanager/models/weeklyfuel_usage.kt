package com.example.utilitymanager.models

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.utilitymanager.R
import com.google.firebase.database.FirebaseDatabase

class weeklyfuel_usage : AppCompatActivity() {

    private val weeks = arrayOf("1", "2", "3", "4");
    private lateinit var fuelUsage: EditText
    private lateinit var distance: EditText
    private lateinit var fuelAmount: EditText
    private lateinit var calculate: Button
    private lateinit var db: FirebaseDatabase
    private lateinit var spinner2: Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weeklyfuel_usage)

        fuelUsage = findViewById(R.id.editTextTextPersonName11)
        distance = findViewById(R.id.editTextTextPersonName12)
        fuelAmount = findViewById(R.id.editTextTextPersonName13)

//        val calculate = findViewById<Button>(R.id.button13)
//
//        calculate.setOnClickListener {
//            startActivity(Intent(this,register_vehicle::class.java))
//        }

        calculate = findViewById(R.id.button13)
        calculate.setOnClickListener {
            saveDataToFirebase()
        }

        val button2 = findViewById<Button>(com.example.utilitymanager.R.id.button2)
        button2.setOnClickListener {
            startActivity(Intent(this, remaining_fuel::class.java))
        }

        spinner2 = findViewById(R.id.spinner2)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weeks)
        spinner2.adapter = arrayAdapter
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Toast.makeText(applicationContext, "selected player is: " + users[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        db = FirebaseDatabase.getInstance()
}
    private fun saveDataToFirebase() {

        Log.d("weeklyfuel_usage", "saveDataToFirebase() called")
        val selectedWeek = weeks[spinner2.selectedItemPosition]
        val fuelUsageValue = fuelUsage.text.toString()
        val distanceValue = distance.text.toString()
        val fuelAmountValue = fuelAmount.text.toString()

        Log.d("weeklyfuel_usage", "selected week: $selectedWeek")
        Log.d("weeklyfuel_usage", "fuel usage: $fuelUsageValue")
        Log.d("weeklyfuel_usage", "distance: $distanceValue")
        Log.d("weeklyfuel_usage", "fuel amount: $fuelAmountValue")

        val weeklyFuelUsage = WeeklyFuelUsage(
            week = selectedWeek,
            fuelUsage = fuelUsageValue,
            distance = distanceValue,
            fuelAmount = fuelAmountValue
        )

        val dbRef = db.getReference("fuel")
        dbRef.child(selectedWeek).setValue(weeklyFuelUsage)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    data class WeeklyFuelUsage(
        val week: String = "",
        val fuelUsage: String = "",
        val distance: String = "",
        val fuelAmount: String = ""
    )


    }