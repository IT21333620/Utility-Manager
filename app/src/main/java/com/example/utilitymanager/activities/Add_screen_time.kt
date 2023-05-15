package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.utilitymanager.R
import com.example.utilitymanager.dataClasses.App
import com.example.utilitymanager.models.ScreenModel
import com.google.firebase.auth.FirebaseAuth
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
        val appName = intent.getParcelableExtra<App>("app")?.name ?:""

        if (screTime.isEmpty()){
            Toast.makeText(this, "Please enter Screen time", Toast.LENGTH_SHORT).show()
        }
        if (device.isEmpty()){
            devices.error = "Please enter Number of Devices"
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?:""
        val screenId = dbRef.push().key!!

        val screen = ScreenModel(
            screenId,
            appName,
            screTime,
            device,
            userId
        )

        dbRef.child(screenId).setValue(screen)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()
                screenTime.setText("")
                devices.setText("")
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}

