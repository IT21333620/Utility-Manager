package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.utilitymanager.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Add_screen_time : AppCompatActivity() {

    private lateinit var screenTime: EditText
    private lateinit var devices: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_screen_time)


        val app = intent.getParcelableExtra<App>("app")
        if (app !=null){
            val imageView : ImageView = findViewById(R.id.imgOther)
            val textView : TextView = findViewById(R.id.textView39)

            imageView.setImageResource(app.image)
            textView.text = app.name

        }


        screenTime = findViewById(R.id.editTextTextPersonName12)
        devices = findViewById(R.id.editTextTextPersonName11)
        btnSaveData = findViewById(R.id.button11)

        dbRef = FirebaseDatabase.getInstance().getReference("ScreenTime")

        btnSaveData.setOnClickListener {
            saveScreenTime()
        }

    }

    private fun saveScreenTime(){

        //getting values
        val screTime = screenTime.text.toString()
        val device = devices.text.toString()

        if (screTime.isEmpty()){
            screenTime.error = "Please enter Screen time"
        }
        if (device.isEmpty()){
            devices.error = "Please enter Number of Devices"
        }

        val screenId = dbRef.push().key!!

        val screen = ScreenModel(screenId, screTime,device)
        dbRef.child(screenId).setValue(screen)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}