package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.databinding.ActivityEditGasDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class Edit_gas_detail : AppCompatActivity() {

    private lateinit var binding : ActivityEditGasDetailBinding
    private lateinit var database : DatabaseReference
    private lateinit var cylinderSize: TextView
    private lateinit var numOfBuners: TextView
    private lateinit var burnRate: TextView
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_gas_detail)

       initView()
        setValueToViews()

    }
    private fun initView(){
        cylinderSize = findViewById(R.id.cylinderSize)
        numOfBuners = findViewById(R.id.numBerners)
        burnRate = findViewById(R.id.burnRate)
        btnUpdate = findViewById(R.id.btnSave)

        btnUpdate.setOnClickListener {
            updateGas()
        }
    }

    private fun setValueToViews(){
        cylinderSize.text = intent.getStringExtra("cylinderSize")
        numOfBuners.text = intent.getStringExtra("numBerners")
        burnRate.text = intent.getStringExtra("burnRate")
    }

    private fun updateGas(){
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val gasId = intent.getStringExtra("gasId") ?: ""
        val cylSize = cylinderSize.text.toString().trim()
        val number = numOfBuners.text.toString().trim()
        val rate = burnRate.text.toString().trim()

        if (cylSize.isEmpty() || number.isEmpty() || rate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val gasRef = FirebaseDatabase.getInstance().getReference("Gas").child(gasId)

        val updateMap = mapOf<String, Any>(
            "cylSize" to cylSize,
            "number" to number,
            "rate" to rate
        )

        gasRef.updateChildren(updateMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Gas updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to update Gas", Toast.LENGTH_SHORT).show()
            }
    }

}