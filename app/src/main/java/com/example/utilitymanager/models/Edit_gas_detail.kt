package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.databinding.ActivityEditGasDetailBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Edit_gas_detail : AppCompatActivity() {

    private lateinit var binding : ActivityEditGasDetailBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditGasDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button20.setOnClickListener {

            val cylinderSize = binding.cylinderSize.text.toString()
            val numBerners = binding.numBerners.text.toString()
            val burnRate = binding.burnRate.text.toString()

            UpdateData(cylinderSize, numBerners, burnRate)

        }

    }

    private fun UpdateData(cylinderSize: String, numBerners: String, burnRate: String) {

        database = FirebaseDatabase.getInstance().getReference("Gas")
        val gas = mapOf<String,String>(
            "numBerners" to numBerners,
            "burnRate" to burnRate

        )

        database.child(cylinderSize).updateChildren(gas).addOnSuccessListener {

            binding.cylinderSize.text.clear()
            binding.numBerners.text.clear()
            binding.burnRate.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }

    }
}