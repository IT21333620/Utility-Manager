package com.example.utilitymanager.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.utilitymanager.models.vehicleModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class register_vehicle : AppCompatActivity() {

    private lateinit var vehicleName: EditText
    private lateinit var brand: EditText
    private lateinit var model: EditText
    private lateinit var year: EditText
    private lateinit var button: Button
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.utilitymanager.R.layout.activity_register_vehicle)

        vehicleName = findViewById(com.example.utilitymanager.R.id.editTextTextPersonName)
        brand = findViewById(com.example.utilitymanager.R.id.editTextTextPersonName24)
        model = findViewById(com.example.utilitymanager.R.id.editTextTextPersonName24)
        year = findViewById(com.example.utilitymanager.R.id.editTextTextPersonName27)
        button = findViewById(com.example.utilitymanager.R.id.button4)

        dbRef = FirebaseDatabase.getInstance().getReference("Vehicle")

        button.setOnClickListener {
            saveDataToFirebase()
            startActivity(Intent(this, weeklyfuel_usage::class.java))
        }

        val button27 = findViewById<Button>(com.example.utilitymanager.R.id.button27)
        button27.setOnClickListener {
            startActivity(Intent(this, weeklyfuel_usage::class.java))
        }
    }

    private fun saveDataToFirebase() {

        val vehicle = vehicleName.text.toString()
        val brandValue = brand.text.toString()
        val modelValue = model.text.toString()
        val yearValue = year.text.toString()

        if(vehicle.isEmpty()){
            vehicleName.error = "Please enter your fuel usage"
        }
        if(brandValue.isEmpty()){
            brand.error = "Please enter distance"
        }
        if(modelValue.isEmpty()){
            model.error = "Please enter your fuel amount"
        }
        if(yearValue.isEmpty()){
            year.error = "Please enter your fuel amount"
        }

        val vehicleId = dbRef.push().key!!

        val Vehicle = vehicleModel(vehicleId, vehicle, brandValue, modelValue, yearValue)

        dbRef.child(vehicleId).setValue(Vehicle)
            .addOnCompleteListener{
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{ err ->
                Toast.makeText( this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }


    }


}