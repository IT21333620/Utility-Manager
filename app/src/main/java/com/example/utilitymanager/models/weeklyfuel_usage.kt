package com.example.utilitymanager.models

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.utilitymanager.FuelModel
import com.example.utilitymanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class weeklyfuel_usage : AppCompatActivity() {

    private val weeks = arrayOf("1", "2", "3", "4");
    private val types = arrayOf("Lanka Petrol 92 Octane", "Lanka Petrol 95 Octane", "Lanka Auto Diesel", "Lanka Super Diesel");
    private lateinit var fuelUsageValue: EditText
    private lateinit var distanceValue: EditText
    private lateinit var fuelAmountValue: EditText
    private lateinit var calculateValue: Button
    private lateinit var dbRef: DatabaseReference
    private lateinit var weekValue: Spinner
    private lateinit var typeValue: Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weeklyfuel_usage)

        fuelUsageValue = findViewById(R.id.editTextTextPersonName11)
        distanceValue = findViewById(R.id.editTextTextPersonName12)
        fuelAmountValue = findViewById(R.id.editTextTextPersonName13)
        calculateValue = findViewById(R.id.button2)
        weekValue = findViewById(R.id.spinner2)
        typeValue = findViewById(R.id.spinner13)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, weeks)
        weekValue.adapter = arrayAdapter
        weekValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val typeArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types)
        typeValue.adapter = typeArrayAdapter
        typeValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        dbRef = FirebaseDatabase.getInstance().getReference("Fuel_Usage")

        calculateValue.setOnClickListener {
            saveDataToFirebase()
            startActivity(Intent(this, remaining_fuel::class.java))
        }

        val button13 = findViewById<Button>(com.example.utilitymanager.R.id.button13)
        button13.setOnClickListener {
            startActivity(Intent(this, register_vehicle::class.java))
        }
    }

    private fun saveDataToFirebase() {
        val week = weeks[weekValue.selectedItemPosition]
        val fuelType = types[typeValue.selectedItemPosition]
        val fuelUsage = fuelUsageValue.text.toString().toDoubleOrNull() ?: 0.0
        val distance = distanceValue.text.toString().toDoubleOrNull() ?: 0.0
        val fuelAmount = fuelAmountValue.text.toString().toDoubleOrNull() ?: 0.0

        if (fuelUsage == 0.0) {
            fuelUsageValue.error = "Please enter your fuel usage"
            return
        }
        if (distance == 0.0) {
            distanceValue.error = "Please enter distance"
            return
        }
        if (fuelAmount == 0.0) {
            fuelAmountValue.error = "Please enter your fuel amount"
            return
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val fuelId = dbRef.push().key!!

        // Set fuel price based on fuel type
        val fuelPrice = when (fuelType) {
            "Lanka Petrol 92 Octane" -> 333.0
            "Lanka Petrol 95 Octane" -> 365.0
            "Lanka Auto Diesel" -> 310.0
            "Lanka Super Diesel" -> 330.0
            else -> 0.0
        }

        val fuelUsed = distance / fuelUsage
        val remainingFuel = fuelAmount - fuelUsed
        val totalCost = fuelUsed * fuelPrice

        val fuel = FuelModel(
            fuelId,
            week,
            fuelUsage.toString(),
            distance.toString(),
            fuelAmount.toString(),
            fuelType,
            userId,
            fuelUsed.toString(),
            remainingFuel.toString(),
            totalCost.toString()
        )

        dbRef.child(fuelId).setValue(fuel)
            .addOnCompleteListener {
                Toast.makeText(
                    this@weeklyfuel_usage,
                    "Data Inserted Successfully",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@weeklyfuel_usage, remaining_fuel::class.java)
                intent.putExtra("fuelPrice", fuelPrice)
                intent.putExtra("fuelType", fuelType)

                startActivity(intent)

            }.addOnFailureListener { err ->
                Toast.makeText(this@weeklyfuel_usage, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}