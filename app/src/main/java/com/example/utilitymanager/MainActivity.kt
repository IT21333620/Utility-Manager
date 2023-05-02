package com.example.utilitymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.utilitymanager.models.other_utilities
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgElectrocity:ImageView = findViewById(R.id.imgElec)
        val imgWater:ImageView = findViewById(R.id.imgWater)
        val imgHome:FloatingActionButton = findViewById(R.id.btnHome)
        val imgGas:ImageView = findViewById(R.id.imgGas)
        val imgOther:ImageView = findViewById(R.id.imgOther)
        val fragmentElectrocity = Electrocity_fragment()
        val fragmentWater = Water_fragment()
        val fragmentHome = Home_fragment()
        val fragmentFuel = fuel_fragment()
        val fragmentOther = other_utilities()
        val Firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()



        imgElectrocity.setOnClickListener {
            imgElectrocity.setImageResource(R.drawable.selected_electro)
            imgWater.setImageResource(R.drawable.unselecteed_water)
            imgGas.setImageResource(R.drawable.unselected_fuel)
            imgOther.setImageResource(R.drawable.unselected)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,fragmentElectrocity)
                commit()
            }
        }

        imgWater.setOnClickListener {
            imgElectrocity.setImageResource(R.drawable.unselected_electro)
            imgWater.setImageResource(R.drawable.selected_water)
            imgGas.setImageResource(R.drawable.unselected_fuel)
            imgOther.setImageResource(R.drawable.unselected)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,fragmentWater)
                commit()
            }
        }

        imgHome.setOnClickListener {
            imgElectrocity.setImageResource(R.drawable.unselected_electro)
            imgWater.setImageResource(R.drawable.unselecteed_water)
            imgGas.setImageResource(R.drawable.unselected_fuel)
            imgOther.setImageResource(R.drawable.unselected)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,fragmentHome)
                commit()
            }
        }

        imgGas.setOnClickListener {
            imgElectrocity.setImageResource(R.drawable.unselected_electro)
            imgWater.setImageResource(R.drawable.unselecteed_water)
            imgGas.setImageResource(R.drawable.selected_fuel)
            imgOther.setImageResource(R.drawable.unselected)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,fragmentFuel)
                commit()
            }
        }

        imgOther.setOnClickListener {
            imgElectrocity.setImageResource(R.drawable.unselected_electro)
            imgWater.setImageResource(R.drawable.unselecteed_water)
            imgGas.setImageResource(R.drawable.unselected_fuel)
            imgOther.setImageResource(R.drawable.selected_other)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,fragmentFuel)
                commit()
            }
        }
    }
}