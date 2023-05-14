package com.example.utilitymanager.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.utilitymanager.R
import com.example.utilitymanager.activities.recyclierView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Electrocity_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_electrocity_fragment, container, false)
        val FragmentConfig = view_configration_electricity()
        val btnNewConfiguration = view.findViewById<FloatingActionButton>(R.id.btnElectroUpdate)
        val btnViewConfig = view.findViewById<FloatingActionButton>(R.id.btnViewElec)

        btnViewConfig.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,FragmentConfig)
                .commit()
        }

        btnNewConfiguration.setOnClickListener {
            val intent = Intent(context, recyclierView::class.java)
            startActivity(intent)
        }


        return view


    }

}