package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.adpters.GasAdapter
import com.example.utilitymanager.adpters.ItemAdepter
import com.example.utilitymanager.dataClasses.Gas
import com.example.utilitymanager.models.ElectroItemModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class available_hrs_in_gas : AppCompatActivity() {

    private lateinit var gasRecyclerView: RecyclerView
    private lateinit var gasList: ArrayList<Gas>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_hrs_in_gas)

        gasRecyclerView = findViewById(R.id.recyleViewGas)
        gasRecyclerView.layoutManager = LinearLayoutManager(this)
        gasRecyclerView.setHasFixedSize(true)

        gasList = arrayListOf<Gas>()

        getGasData()

    }
    private fun getGasData(){

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val query = FirebaseDatabase.getInstance().getReference("Gas")
            .orderByChild("userId")
            .equalTo(userId)

        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                gasList.clear()
                if (snapshot.exists()){
                    for (itemSnap in snapshot.children){
                        val gasData = itemSnap.getValue(Gas::class.java)
                        gasList.add(gasData!!)

                        val cylinderSize = gasData.cylinderSize!!.toDouble()
                        val numBerners = gasData.numBerners!!.toInt()
                        val burnRate = gasData.burnRate!!.toDouble()
                        val units = (cylinderSize * numBerners * burnRate)
                    }
                    val mAdapter = GasAdapter(gasList)
                    gasRecyclerView.adapter = mAdapter

                    mAdapter.setOnGasClickListener(object : GasAdapter.onGasClickListener{
                        override fun onGasClick(position: Int) {
                            val intent = Intent(this@available_hrs_in_gas, Edit_gas_detail::class.java)
                            intent.putExtra("gasId",gasList[position].gasId)
                            intent.putExtra("cylinderSize",gasList[position].cylinderSize)
                            intent.putExtra("numBerners",gasList[position].numBerners)
                            intent.putExtra("burnRate",gasList[position].burnRate)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
