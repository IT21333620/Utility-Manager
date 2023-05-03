package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.utilitymanager.R

class details_electricity : AppCompatActivity() {

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
    }
}