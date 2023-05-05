package com.example.utilitymanager.models

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.utilitymanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class update_electricity_config : AppCompatActivity() {

    private lateinit var itemTitle: TextView
    private lateinit var itemImage: ImageView
    private lateinit var itemWatts: TextView
    private lateinit var itemNumber: TextView
    private lateinit var itemHours: TextView
    private lateinit var btnUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_electricity_config)

        initView()
        setValueToViews()
    }

    private fun initView(){

        itemTitle = findViewById(R.id.itemTitle)
        itemWatts = findViewById(R.id.itemWatts)
        itemNumber = findViewById(R.id.itemNumber)
        itemHours = findViewById(R.id.itemHours)
        btnUpdate = findViewById(R.id.btnUpdate)
        itemImage = findViewById(R.id.imgUpdateElec)

        itemImage.setImageResource(intent.getIntExtra("Img", 0))


        btnUpdate.setOnClickListener {
            updateItemDetails()
        }

    }

    private fun setValueToViews(){
        itemTitle.text = intent.getStringExtra("title")
        itemWatts.text = intent.getStringExtra("watts")
        itemNumber.text = intent.getStringExtra("number")
        itemHours.text = intent.getStringExtra("hours")

    }

    private fun updateItemDetails() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val itemId = intent.getStringExtra("itemId") ?: ""
        val watts = itemWatts.text.toString().trim()
        val number = itemNumber.text.toString().trim()
        val hours = itemHours.text.toString().trim()


        if (watts.isEmpty() || number.isEmpty() || hours.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val itemRef = FirebaseDatabase.getInstance().getReference("electric_item").child(itemId)

        val updateMap = mapOf<String, Any>(
            "watts" to watts,
            "number" to number,
            "hours" to hours
        )

        itemRef.updateChildren(updateMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Item updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to update item", Toast.LENGTH_SHORT).show()
            }
    }

}