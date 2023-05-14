package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.fragments.Water_fragment
import com.example.utilitymanager.models.WaterModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class user_information : AppCompatActivity() {

    private lateinit var etMembersCount : EditText
    private lateinit var etWeek : EditText
    private lateinit var etDrinking : EditText
    private lateinit var etShowering : EditText
    private lateinit var etWashing : EditText
    private lateinit var etGardening : EditText
    private lateinit var etOther : EditText
    private lateinit var btnSave : Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        etMembersCount = findViewById(R.id.editTextTextPersonName)
        etWeek = findViewById(R.id.editTextTextPersonName2)
        etDrinking = findViewById(R.id.editTextTextPersonName6)
        etShowering = findViewById(R.id.editTextTextPersonName4)
        etWashing = findViewById(R.id.editTextTextPersonName5)
        etGardening = findViewById(R.id.editTextTextPersonName7)
        etOther = findViewById(R.id.editTextTextPersonName8)
        btnSave = findViewById(R.id.signUp)

        dbRef = FirebaseDatabase.getInstance().getReference("Water")

        btnSave.setOnClickListener {

            val memberCount = etMembersCount.text.toString()
            val week = etWeek.text.toString()

            if (!week.matches("week[1-5]".toRegex())) {
                // Display error message for invalid week format
                Toast.makeText(this, "Enter the week as 'week1', 'week2', 'week3', 'week4', or 'week5'", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!memberCount.matches("-?\\d+(\\.\\d+)?".toRegex())) {
                // Display error message for invalid number
                Toast.makeText(this, "Enter a valid number for MembersCount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveWaterData()

            val waterForDrinking = etDrinking.text.toString().toDoubleOrNull() ?: 0.0
            val waterForShowering = etShowering.text.toString().toDoubleOrNull() ?: 0.0
            val waterForWashing = etWashing.text.toString().toDoubleOrNull() ?: 0.0
            val waterForGardening = etGardening.text.toString().toDoubleOrNull() ?: 0.0
            val waterForOther =etOther.text.toString().toDoubleOrNull() ?: 0.0

            val totalWaterConsumed = waterForDrinking + waterForShowering + waterForWashing + waterForGardening +  waterForOther


//            startActivity(Intent(this, weekly_usage::class.java))

            val intent = Intent(this, weekly_usage::class.java)
            intent.putExtra("TOTAL_WATER_CONSUMED", totalWaterConsumed)

            startActivity(intent)

        }

        val backbutton = findViewById<Button>(R.id.signUp2)

        backbutton.setOnClickListener{
            startActivity(Intent( this, Water_fragment::class.java))
        }



    }
    private fun saveWaterData(){

        //getting values
        val memberCount = etMembersCount.text.toString()
        val week = etWeek.text.toString()
        val drinking = etDrinking.text.toString()
        val showering = etShowering.text.toString()
        val washing = etWashing.text.toString()
        val gardening = etGardening.text.toString()
        val other = etOther.text.toString()

        if (memberCount.isEmpty()){
            etMembersCount.error = "Please enter no of members"
        }
        if (week.isEmpty()){
            etWeek.error = "Please enter the week"
        }

        if (drinking.isEmpty()){
            etDrinking.error = "Please enter the water amount"
        }
        if (showering.isEmpty()){
            etShowering.error = "Please enter the water amount"
        }
        if (washing.isEmpty()){
            etWashing.error = "Please enter the water amount"
        }
        if (gardening.isEmpty()){
            etGardening.error = "Please enter the water amount"
        }
        if (other.isEmpty()){
            etOther.error = "Please enter the water amount"
        }
        val watId = dbRef.push().key!!

        val water = WaterModel(watId, memberCount, week, drinking, showering, washing, gardening, other)

        dbRef.child(week).setValue(water)
            .addOnCompleteListener{
                Toast.makeText( this, "Data inserted successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{ err ->
                Toast.makeText( this, "Error ${err.message}",Toast.LENGTH_LONG).show()
            }
    }


}