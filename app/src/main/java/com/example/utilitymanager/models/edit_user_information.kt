package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.databinding.ActivityEditUserInformationBinding
import com.example.utilitymanager.water_historylist
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class edit_user_information : AppCompatActivity() {

    private lateinit var binding : ActivityEditUserInformationBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button10.setOnClickListener {

            val memberCount = binding.editTextTextPersonName3.text.toString()
            val week = binding.editTextTextPersonName9.text.toString()
            val drinking = binding.editTextTextPersonName11.text.toString()
            val showering = binding.editTextTextPersonName10.text.toString()
            val washing = binding.editTextTextPersonName13.text.toString()
            val gardening = binding.editTextTextPersonName15.text.toString()
            val other = binding.editTextTextPersonName14.text.toString()

            updateData(memberCount, week, drinking, showering, washing, gardening, other )


        }

        val ButtonOpen : Button = findViewById(R.id.button29)
        ButtonOpen.setOnClickListener{

            startActivity(Intent( this, water_historylist::class.java))

        }
    }

    private fun updateData(memberCount: String, week: String, drinking: String,showering: String, washing: String, gardening: String, other: String) {

        database = FirebaseDatabase.getInstance().getReference("Water")
        val waterlist = mapOf<String, String>(
            "memberCount" to memberCount,
            "week" to week,
            "drinking" to drinking,
            "showering" to showering,
            "washing" to washing,
            "gardening" to gardening,
            "other" to other

        )

        database.child(week).updateChildren(waterlist).addOnSuccessListener {

            binding.editTextTextPersonName3.text.clear()
            binding.editTextTextPersonName9.text.clear()
            binding.editTextTextPersonName11.text.clear()
            binding.editTextTextPersonName10.text.clear()
            binding.editTextTextPersonName13.text.clear()
            binding.editTextTextPersonName15.text.clear()
            binding.editTextTextPersonName14.text.clear()

            Toast.makeText(this,"Successfully update",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener(){

            Toast.makeText(this,"Failed to update",Toast.LENGTH_SHORT).show()
        }
    }


}