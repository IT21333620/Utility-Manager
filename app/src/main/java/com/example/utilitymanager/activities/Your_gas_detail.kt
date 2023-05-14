package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.databinding.ActivityYourGasDetailBinding
import com.example.utilitymanager.dataClasses.Gas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class your_gas_detail : AppCompatActivity() {

    private lateinit var binding : ActivityYourGasDetailBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourGasDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button18.setOnClickListener {
            val cylinderSize = binding.cylinderSize.text.toString()
            val numBerners = binding.numBerners.text.toString()
            val burnRate = binding.burnRate.text.toString()

            // Convert input values to Double
            val cylinderSizeDouble = cylinderSize.toDouble()
            val numBernersDouble = numBerners.toDouble()
            val burnRateDouble = burnRate.toDouble()

            database = FirebaseDatabase.getInstance().getReference("Gas")
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            val gasId = database.push().key!!

            // Calculate available hours remaining
            val availableHours = (cylinderSizeDouble * 14.2 * 0.8) / (numBernersDouble * burnRateDouble)

            // Convert availableHours to String before saving to the database
            val availableHoursString = availableHours.toString()

            // Create Gas object with all the data
            val gas = Gas(gasId,cylinderSize, numBerners, burnRate, userId)

            // Save Gas object to the database
            database.child(cylinderSize).setValue(gas)
                .addOnSuccessListener {
                    binding.cylinderSize.text.clear()
                    binding.numBerners.text.clear()
                    binding.burnRate.text.clear()

                    Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }
        }

        val buttonutton = findViewById<Button>(R.id.button27)

        buttonutton.setOnClickListener {
            val intent = Intent(this@your_gas_detail, available_hrs_in_gas::class.java)
            val cylinderSize = binding.cylinderSize.text.toString()
            intent.putExtra("cylinderSize", cylinderSize)
            startActivity(intent)
        }
    }
}