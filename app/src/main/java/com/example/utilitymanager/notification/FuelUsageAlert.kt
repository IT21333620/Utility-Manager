package com.example.utilitymanager.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.utilitymanager.R

class fuelUsageAlert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_usage_alert)

        val buttonDismiss: Button = findViewById(R.id.buttonDismiss)
        buttonDismiss.setOnClickListener {
            // Dismiss the alert and close the activity
            finish()
        }
    }
}