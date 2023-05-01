package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R
import com.google.android.material.textfield.TextInputEditText

class register : AppCompatActivity() {

    private lateinit var enterEmail: TextInputEditText
    private lateinit var enterPassword: TextInputEditText
    private lateinit var reEnterPassword: TextInputEditText
    private lateinit var register: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        enterEmail = findViewById<TextInputEditText>(R.id.loginPassword)



    }
}