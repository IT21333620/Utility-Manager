package com.example.utilitymanager.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.utilitymanager.R
import com.example.utilitymanager.activities.monthly_water_bill
import com.example.utilitymanager.activities.user_information
import com.example.utilitymanager.activities.water_historylist
import com.example.utilitymanager.activities.weekly_history

class Water_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_water_fragment, container, false)

        // Find the Button view and set an OnClickListener on it
        val button = view.findViewById<Button>(R.id.button22)
        button.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, user_information::class.java)
            startActivity(intent)
        }

        val buttonbill = view.findViewById<Button>(R.id.button34)
        buttonbill.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, water_historylist::class.java)
            startActivity(intent)
        }


        return view
    }
}