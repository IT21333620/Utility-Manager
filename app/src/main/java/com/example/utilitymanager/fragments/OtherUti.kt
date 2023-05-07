package com.example.utilitymanager.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.NonNull
import com.example.utilitymanager.R
import com.example.utilitymanager.activities.Networkmain
import com.example.utilitymanager.activities.your_gas_detail


class OtherUti : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(@NonNull inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_other_uti, container, false)

        // Find the Button view and set an OnClickListener on it
        val button = view.findViewById<Button>(R.id.button13)
        val button2 = view.findViewById<Button>(R.id.button22)
        button.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, your_gas_detail::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            // Create an Intent to navigate to the desired activity
            val intent = Intent(activity, Networkmain::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return view
    }
}