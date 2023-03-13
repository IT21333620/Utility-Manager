package com.example.utilitymanager.models

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class register_vehicle : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.utilitymanager.R.layout.activity_register_vehicle)

        val dropdown = findViewById<Spinner>(R.id.list)
        val items = arrayOf("Toyota", "Suzuki", "Nissan", "Hyundai", "BMW", "Audi", "Honda", "other")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)
        dropdown.adapter = adapter

    }
}