package com.example.utilitymanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.adpters.ItemAdepter
import com.example.utilitymanager.models.ElectroItemModel
import com.example.utilitymanager.models.electricity_history
import com.example.utilitymanager.models.update_electricity_config
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue


class view_configration_electricity : Fragment() {

    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemList: ArrayList<ElectroItemModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_configration_electricity, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRecyclerView = view.findViewById(R.id.recView)
        itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemRecyclerView.setHasFixedSize(true)

        itemList = arrayListOf<ElectroItemModel>()

        getItemData()


    }



    private fun getItemData(){

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val query = FirebaseDatabase.getInstance().getReference("electric_item")
            .orderByChild("userId")
            .equalTo(userId)

        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                var totalUnit = 0.0
                if (snapshot.exists()){
                    for (itemSnap in snapshot.children){
                        val itemData = itemSnap.getValue(ElectroItemModel::class.java)
                        itemList.add(itemData!!)

                        val watts = itemData.watts!!.toDouble()
                        val items = itemData.number!!.toInt()
                        val hours = itemData.hours!!.toDouble()
                        val units = (watts * hours * items) / 1000.0

                        // accumulate unit price to total
                        totalUnit += units * 30
                    }
                    val mAdapter = ItemAdepter(itemList)
                    itemRecyclerView.adapter = mAdapter

                    val btnNewConfiguration = view?.findViewById<Button>(R.id.btnElecHistory)
                    val eleEstUsage = view?.findViewById<TextView>(R.id.eleEstUsage)
                    if (eleEstUsage != null) {
                        eleEstUsage.text = String.format("%.2f", totalUnit)
                        val value = eleEstUsage.text.toString().toString()

                        if (btnNewConfiguration != null) {
                            btnNewConfiguration.setOnClickListener {
                                val intent = Intent(context, electricity_history::class.java)
                                intent.putExtra("usage", value)
                                startActivity(intent)
                            }
                        }
                    }

                    mAdapter.setOnItemClickListener(object : ItemAdepter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(requireContext(), update_electricity_config::class.java)
                            intent.putExtra("itemId",itemList[position].itemId)
                            intent.putExtra("watts",itemList[position].watts)
                            intent.putExtra("number",itemList[position].number)
                            intent.putExtra("hours",itemList[position].hours)
                            intent.putExtra("title",itemList[position].title)
                            intent.putExtra("itemUrl",itemList[position].itemUrl)
                            intent.putExtra("Img",itemList[position].image)
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