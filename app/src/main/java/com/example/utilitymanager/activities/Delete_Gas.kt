package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utilitymanager.databinding.ActivityDeleteGasBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Delete_Gas : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteGasBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteGasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gasDelete.setOnClickListener {

            var cylinderSize = binding.cylinderSize.text.toString()
            if (cylinderSize.isNotEmpty())
                deleteData(cylinderSize)
            else
                Toast.makeText(this,"Please enter th Cylinder Size",Toast.LENGTH_SHORT).show()

        }


        }


    private fun deleteData(cylinderSize: String) {
        database = FirebaseDatabase.getInstance().getReference("Gas")
        database.child(cylinderSize.toString()).removeValue().addOnSuccessListener {
            binding.cylinderSize.text.clear()
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }

    }





}