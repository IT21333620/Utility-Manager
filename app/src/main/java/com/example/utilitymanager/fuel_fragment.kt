package com.example.utilitymanager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.utilitymanager.models.fuel_usage_history
import com.example.utilitymanager.models.register_vehicle

class fuel_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fuel_fragment, container, false)

        // Find the Button view and set an OnClickListener on it
        val button = view.findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, register_vehicle::class.java)
            startActivity(intent)
        }

        val history = view.findViewById<Button>(R.id.button)
        history.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, fuel_recyclerView::class.java)
            startActivity(intent)
        }

        return view
    }
}