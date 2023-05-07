package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.R
import com.example.utilitymanager.dataClasses.Gas
import com.google.firebase.database.*

class available_hrs_in_gas : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var cylinderSize: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_hrs_in_gas)

        val dataTextView = findViewById<TextView>(R.id.editTextTextPersonName20)
        val deleteGasButton = findViewById<Button>(R.id.button16)
        val updateButton = findViewById<Button>(R.id.button15)
        val loginButton = findViewById<Button>(R.id.loginSignIn)

        // Get the cylinderSize value from the Intent extras
        cylinderSize = intent.getStringExtra("cylinderSize").toString()

        // Get a reference to the Gas node in the database
        database = FirebaseDatabase.getInstance().getReference("Gas")

        // Retrieve the data for the specific cylinderSize
        database.child(cylinderSize).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Extract the availableHours value from the retrieved data
                    val gas = snapshot.getValue(Gas::class.java)
                    val availableHours = gas?.availableHoursString

                    // Set the text of the TextView to the availableHours value
                    dataTextView.text = availableHours
                } else {
                    dataTextView.text = "Data not found"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                dataTextView.text = "Error: ${error.message}"
            }
        })

        loginButton.setOnClickListener {
            val intent = Intent(this@available_hrs_in_gas, Weekly_gas_usage::class.java)
            startActivity(intent)
        }

        updateButton.setOnClickListener {
            val intent = Intent(this@available_hrs_in_gas, Edit_gas_detail::class.java)
            intent.putExtra("cylinderSize", cylinderSize)
            startActivity(intent)
        }

        deleteGasButton.setOnClickListener {
            val intent = Intent(this@available_hrs_in_gas, Delete_Gas::class.java)
            intent.putExtra("cylinderSize", cylinderSize)
            startActivity(intent)
        }
    }
}
