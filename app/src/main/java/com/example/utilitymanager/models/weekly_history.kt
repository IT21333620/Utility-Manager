package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R

class weekly_history : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val myButton3 = findViewById<Button>(R.id.button24)
        myButton3.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, edit_user_information::class.java)
            startActivity(intent)
        }

        val myButton4 = findViewById<Button>(R.id.button28)
        myButton4.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, edit_user_information::class.java)
            startActivity(intent)
        }

        val myButton5 = findViewById<Button>(R.id.button30)
        myButton5.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, edit_user_information::class.java)
            startActivity(intent)
        }

        val myButton6 = findViewById<Button>(R.id.button32)
        myButton6.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, edit_user_information::class.java)
            startActivity(intent)
        }

        val ButtonOpen : Button = findViewById(R.id.button33)
        ButtonOpen.setOnClickListener{

            startActivity(Intent( this, weekly_usage::class.java))

        }
    }
}