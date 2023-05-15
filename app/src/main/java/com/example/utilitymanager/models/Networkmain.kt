package com.example.utilitymanager.models

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.utilitymanager.OtherUti
import com.example.utilitymanager.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Networkmain : AppCompatActivity() {

    private lateinit var dataAmount: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networkmain)

        dataAmount = findViewById(R.id.editTextTextPersonName)
        btnSaveData = findViewById(R.id.button4)


        dbRef = FirebaseDatabase.getInstance().getReference("month_data")


        btnSaveData.setOnClickListener {
            saveMonthData()
        }


        //netAvailableData = findViewById(R.id.textView17)
        val buttonutton3 = findViewById<Button>(R.id.button14)
        val buttonutton4 = findViewById<Button>(R.id.button19)

        buttonutton3.setOnClickListener {
            startActivity(Intent(this@Networkmain, AllAppScreen::class.java))
        }

        buttonutton4.setOnClickListener {
            startActivity(Intent(this@Networkmain, DataUsage::class.java))
        }


    }


    private fun saveMonthData() {
        //getting values
        val DataAmount = dataAmount.text.toString()

        if (DataAmount.isEmpty()) {
            dataAmount.error = "Please enter monthly data amount"
        }

        val dataId = dbRef.push().key!!

        val data = DataModel(dataId, DataAmount)

        dbRef.child(dataId).setValue(data)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }


    }


}