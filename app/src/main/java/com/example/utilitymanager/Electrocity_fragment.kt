package com.example.utilitymanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Electrocity_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_electrocity_fragment, container, false)
        val FragmentElecItem = electricity_item_fragment()
        val FragmentConfig = view_configration_electricity()
        val btnNewConfiguration = view.findViewById<Button>(R.id.btnNewConfig)
        val btnViewConfig = view.findViewById<Button>(R.id.btnViewConfig)

        btnViewConfig.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,FragmentConfig)
                .commit()
        }

        btnNewConfiguration.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2,FragmentElecItem)
                .commit()
        }


        return view


    }

}