package com.example.utilitymanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.adpters.UsageAdapter
import com.example.utilitymanager.dataClasses.Usage
import com.google.firebase.database.*

class DataUsage : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var usageArrayList: ArrayList<Usage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_usage)

        userRecyclerView = findViewById(R.id.usageList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        usageArrayList = arrayListOf<Usage>()
        getUsageData()

    }

    private fun getUsageData() {
        dbref = FirebaseDatabase.getInstance().getReference("ScreenTime")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (usageSnapshot in snapshot.children){

                        val usage = usageSnapshot.getValue(Usage::class.java)
                        usageArrayList.add(usage!!)

                    }

                    userRecyclerView.adapter = UsageAdapter(usageArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}