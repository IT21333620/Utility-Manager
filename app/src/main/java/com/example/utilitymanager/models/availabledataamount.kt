package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.utilitymanager.R
import com.google.firebase.database.*

class availabledataamount : AppCompatActivity() {

    private lateinit var netAvailableData: TextView
    private lateinit var dbRef: DatabaseReference


    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("month_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_availabledataamount)



        //netAvailableData = findViewById(R.id.textView17)
        val buttonutton = findViewById<Button>(R.id.button7)
        val buttonutton2 = findViewById<Button>(R.id.button10)

        buttonutton2.setOnClickListener {
            var intent = Intent(this@availabledataamount, AllAppScreen::class.java)
            startActivity(intent)
        }

        buttonutton.setOnClickListener {
            var intent = Intent(this@availabledataamount, DataUsage::class.java)
            startActivity(intent)
        }

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Retrieve data from the snapshot
                val value = dataSnapshot.child("dataAmount").value

                // Do something with the data
                // ...
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors
                // ...
            }
        })




    }


}