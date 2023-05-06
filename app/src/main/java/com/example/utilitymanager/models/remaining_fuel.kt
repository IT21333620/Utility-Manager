package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.FuelModel
import com.example.utilitymanager.R
import com.example.utilitymanager.fuelUsageAlert
import com.example.utilitymanager.fuel_recyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class remaining_fuel : AppCompatActivity() {

    private lateinit var fuelUsedValue: TextView
    private lateinit var remainingFuelValue: TextView
    private lateinit var totalCostValue: TextView
    private lateinit var fuelPriceValue: TextView
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_fuel)

        fuelUsedValue = findViewById(R.id.textView18)
        remainingFuelValue = findViewById(R.id.textView20)
        totalCostValue = findViewById(R.id.textView24)
        fuelPriceValue = findViewById(R.id.textView22)

        // Get the selected fuel type from the Intent
        val fuelType = intent.getStringExtra("fuelType")

        // Set the fuel price based on the selected fuel type
        val fuelPrice = when (fuelType) {
            "Lanka Petrol 92 Octane" -> 333.0
            "Lanka Petrol 95 Octane" -> 365.0
            "Lanka Auto Diesel" -> 310.0
            "Lanka Super Diesel" -> 330.0
            else -> 0.0
        }

        // Display the fuel price
        fuelPriceValue.text = fuelPrice.toString()

        dbRef = FirebaseDatabase.getInstance().getReference("Fuel_Usage")

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        dbRef.orderByChild("userId").equalTo(userId).limitToLast(1)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children) {
                        val fuelUsed = data.child("fuelUsed").value.toString().toDoubleOrNull() ?: 0.0
                        val distance = data.child("distance").value.toString().toDoubleOrNull() ?: 0.0
                        val fuelAmount = data.child("fuelAmount").value.toString().toDoubleOrNull() ?: 0.0
                        val remainingFuel = data.child("remainingFuel").value.toString().toDoubleOrNull() ?: 0.0
                        val totalCost = data.child("totalCost").value.toString().toDoubleOrNull() ?: 0.0

                        remainingFuelValue.text = String.format("%.2f", remainingFuel)
                        totalCostValue.text = String.format("%.2f", totalCost)
                        fuelUsedValue.text = String.format("%.2f", fuelUsed)
                        fuelPriceValue.text = fuelPrice.toString()

                        // Check fuel consumption against the threshold
                        checkFuelConsumptionThreshold(fuelUsed)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })

        val viewHistory = findViewById<Button>(R.id.button22)

        viewHistory.setOnClickListener {
            startActivity(Intent(this,fuel_recyclerView::class.java))
        }
    }

    private fun checkFuelConsumptionThreshold(fuelUsed: Double) {
        val threshold = 10.0 // desired threshold value

        if (fuelUsed > threshold) {
            sendFuelConsumptionAlert()
        }
    }

    private fun sendFuelConsumptionAlert() {
        val intent = Intent(this, fuelUsageAlert::class.java)
        startActivity(intent)
    }

}