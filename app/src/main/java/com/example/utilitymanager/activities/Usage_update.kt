package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utilitymanager.databinding.ActivityUsageUpdateBinding
import com.example.utilitymanager.models.FuelModel
import com.google.firebase.database.*

class usage_update : AppCompatActivity() {

    private lateinit var binding: ActivityUsageUpdateBinding
    private lateinit var dbRef : DatabaseReference
    private lateinit var fuelId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsageUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fuelId = intent.getStringExtra("fuelId").toString()

        dbRef = FirebaseDatabase.getInstance().getReference("Fuel_Usage").child(fuelId)

        // Get the fuel data for the fuel id
        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val fuel = snapshot.getValue(FuelModel::class.java)
                    if (fuel != null) {

                        // Update the UI with the existing fuel data for the selected id
                        binding.updateweek.setText(fuel.week)
                        binding.updateweek.isEnabled = false // Disable the week field
                        binding.editTextTextPersonName14.setText(fuel.fuelUsage)
                        binding.editTextTextPersonName15.setText(fuel.distance)
                        binding.editTextTextPersonName16.setText(fuel.fuelAmount)
                        binding.editTextTextPersonName18.setText(fuel.fuelType)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@usage_update, "Failed to retrieve fuel data", Toast.LENGTH_SHORT).show()
            }
        })

        binding.button7.setOnClickListener {

            val fuelUsage = binding.editTextTextPersonName14.text.toString()
            val distance = binding.editTextTextPersonName15.text.toString()
            val fuelAmount = binding.editTextTextPersonName16.text.toString()
            val fuelType = binding.editTextTextPersonName18.text.toString()

            updateData(fuelUsage, distance, fuelAmount, fuelType)
        }
    }

    private fun updateData(fuelUsage: String, distance: String, fuelAmount: String, fuelType: String) {

        val fuelUsageValue = fuelUsage.toDoubleOrNull() ?: 0.0
        val distanceValue = distance.toDoubleOrNull() ?: 0.0
        val fuelAmountValue = fuelAmount.toDoubleOrNull() ?: 0.0

        // Calculate total cost, fuel used, and remaining fuel
        val fuelUsed = if (fuelUsageValue != 0.0 && distanceValue != 0.0) {
            distanceValue / fuelUsageValue
        } else {
            0.0
        }
        val totalCost = fuelUsed * getFuelPrice(fuelType)
        val remainingFuel = fuelAmountValue - fuelUsed

        // Create a map of the updated fuel data
        val updatedFuelData = mapOf<String, Any>(
            "week" to binding.updateweek.text.toString(),
            "fuelUsed" to fuelUsed.toString(),
            "totalCost" to totalCost.toString(),
            "remainingFuel" to remainingFuel.toString(),
            "distance" to distance,
            "fuelAmount" to fuelAmount,
            "fuelType" to fuelType,
        )

        // Update the fuel data for the selected fuelId in the Firebase Realtime Database
        dbRef.updateChildren(updatedFuelData).addOnSuccessListener {
            Toast.makeText(this@usage_update, "Successfully updated", Toast.LENGTH_LONG).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@usage_update, "Failed to update fuel data", Toast.LENGTH_LONG).show()
        }
    }

    private fun getFuelPrice(fuelType: String): Double {
        return when (fuelType) {
            "Lanka Petrol 92 Octane" -> 333.0
            "Lanka Petrol 95 Octane" -> 365.0
            "Lanka Auto Diesel" -> 310.0
            "Lanka Super Diesel" -> 330.0
            else -> 0.0
        }}
}
