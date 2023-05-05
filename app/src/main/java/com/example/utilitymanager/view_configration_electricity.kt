package com.example.utilitymanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.adpters.ItemAdepter
import com.example.utilitymanager.models.ElectroItemModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class view_configration_electricity : Fragment() {

    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var itemList: ArrayList<ElectroItemModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_configration_electricity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRecyclerView = view.findViewById(R.id.recView)
        itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemRecyclerView.setHasFixedSize(true)

        itemList = arrayListOf<ElectroItemModel>()

        getItemData()

        // rest of your code
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
                if (snapshot.exists()){
                    for (itemSnap in snapshot.children){
                        val itemData = itemSnap.getValue(ElectroItemModel::class.java)
                        itemList.add(itemData!!)
                    }
                    val mAdapter = ItemAdepter(itemList)
                    itemRecyclerView.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event
            }
        })
    }


}