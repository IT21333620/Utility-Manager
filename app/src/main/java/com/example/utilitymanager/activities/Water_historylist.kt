package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.adpters.WaterAdapter
import com.example.utilitymanager.models.waterlist
import com.google.firebase.database.*

class water_historylist : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var waterRecyclerView : RecyclerView
    private lateinit var waterArrayList: ArrayList<waterlist>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_historylist)



        val myButtonup = findViewById<Button>(R.id.btnUpdate)
        myButtonup.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, edit_user_information::class.java)
            startActivity(intent)
        }

        val myButton31 = findViewById<Button>(R.id.button31)
        myButton31.setOnClickListener {
            // code to be executed when the button is clicked
            // e.g. start a new activity
            val intent = Intent(this, delete_water_data::class.java)
            startActivity(intent)
        }



//        val ButtonOpen2 : Button = findViewById(R.id.button36)
//        ButtonOpen2.setOnClickListener{
//
//            startActivity(Intent( this, weekly_usage::class.java))
//
//        }

        waterRecyclerView = findViewById(R.id.historylist)
        waterRecyclerView.layoutManager = LinearLayoutManager(this)
        waterRecyclerView.setHasFixedSize(true)

        waterArrayList = arrayListOf<waterlist>()
        getWaterData()
    }



    private fun getWaterData() {
        dbref = FirebaseDatabase.getInstance().getReference("Water")

        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(waterSnapshot in snapshot.children){

                        val waterlist = waterSnapshot.getValue(waterlist::class.java)
                        waterArrayList.add(waterlist!!)

                    }

                    waterRecyclerView.adapter = WaterAdapter(waterArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}