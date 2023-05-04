package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.utilitymanager.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class details_electricity : AppCompatActivity() {

    private lateinit var entWatts: EditText
    private lateinit var entNumber: EditText
    private lateinit var entHours: EditText
    private lateinit var btnSubmit: FloatingActionButton

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_electricity)

        val item = intent.getParcelableExtra<Item>("item")
        if (item != null){
            val imageView: ImageView = findViewById(R.id.cardImg)
            val textView: TextView = findViewById(R.id.title)

            textView.text = item.title
            imageView.setImageResource(item.image)
        }

        entWatts = findViewById(R.id.textWatt)
        entNumber = findViewById(R.id.textNumber)
        entHours = findViewById(R.id.TextHours)
        btnSubmit = findViewById(R.id.btnSubmit)


        dbRef = FirebaseDatabase.getInstance().getReference("electric_item")

        btnSubmit.setOnClickListener {
            saveElectriItemData()
        }


    }

    private fun saveElectriItemData()
    {
        val watts = entWatts.text.toString()
        val  number = entNumber.text.toString()
        val hours = entHours.text.toString()
        val itemName = intent.getParcelableExtra<Item>("item")?.title ?: ""
        val itemImage = intent.getParcelableExtra<Item>("item")?.image ?: 0

        if(watts.isEmpty()){
            entWatts.error = "Please enter Watts"
        }
        if(number.isEmpty()){
            entNumber.error = "Please enter Number of items"
        }
        if(hours.isEmpty()){
            entHours.error = "Please enter hours"
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val itemId = dbRef.push().key!!

        val elecItem = ElectroItemModel(
            itemId,
            watts,
            number,
            hours,
            itemName,
            itemImage,
            userId
        )

        dbRef.child(itemId).setValue(elecItem)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted Successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener(){ err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_SHORT).show()
            }

    }
}