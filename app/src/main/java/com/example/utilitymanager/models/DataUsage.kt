package com.example.utilitymanager.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DataUsage : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var usageArrayList: ArrayList<ScreenModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_usage)

        userRecyclerView = findViewById(R.id.usageList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        usageArrayList = arrayListOf<ScreenModel>()
        getUsageData()

    }

    private fun getUsageData() {

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?:""

        val query = FirebaseDatabase.getInstance().getReference("ScreenTime")
            .orderByChild("userId")
            .equalTo(userId)

        query.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                usageArrayList.clear()
                var totalData = 0.0
                if (snapshot.exists()){
                    for (usageSnapshot in snapshot.children){

                        val usage = usageSnapshot.getValue(ScreenModel::class.java)
                        usageArrayList.add(usage!!)


                        val screTime = usage.screTime!!.toDouble()
                        val device = usage.device!!.toInt()
                        val data = (screTime * device)

                        //total data = data * 3mbps
                        totalData += data * 3

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