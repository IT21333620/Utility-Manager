package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utilitymanager.databinding.ActivityDeleteWaterDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class delete_water_data : AppCompatActivity() {

    private lateinit var binding : ActivityDeleteWaterDataBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteWaterDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button40.setOnClickListener{

            var week = binding.editTextTextPersonName17.text.toString()
            if(week.isNotEmpty())
                deleteData(week)
            else
                Toast.makeText(this,"Please enter the week",Toast.LENGTH_LONG).show()

        }

    }


    private fun deleteData(week: String) {
        database = FirebaseDatabase.getInstance().getReference("Water")
        database.child(week).removeValue().addOnSuccessListener {

            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()

        }

    }
}