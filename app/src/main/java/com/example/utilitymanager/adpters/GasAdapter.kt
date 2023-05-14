package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.dataClasses.Gas
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GasAdapter(private val gasList: ArrayList<Gas>)
    : RecyclerView.Adapter<GasAdapter.ViewHolder>(){

    class ViewHolder (gasView: View,clickListener: GasAdapter.onGasClickListener):RecyclerView.ViewHolder(gasView){

        val cylSize: TextView = gasView.findViewById(R.id.gasVol)
        val remain: TextView = gasView.findViewById(R.id.NumOfUnits)
        val btnUpdate: FloatingActionButton = gasView.findViewById(R.id.btnElectroUpdate)
        val btnDelete: Button = gasView.findViewById(R.id.btnElectroDelete)

        init {
            gasView.setOnClickListener{
                clickListener.onGasClick(adapterPosition)
            }
        }
    }

    private lateinit var mListener: onGasClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val gasView = LayoutInflater.from(parent.context).inflate(R.layout.gas_card,parent,false)
        return ViewHolder(gasView,mListener)
    }

    override fun onBindViewHolder(holder: GasAdapter.ViewHolder, position: Int) {
        val currentGas = gasList[position]
        holder.cylSize.text = currentGas.cylinderSize
        holder.btnUpdate.setOnClickListener{
            mListener.onGasClick(position)
        }
        holder.btnDelete.setOnClickListener {
            deleteGas(position)
        }
        val remain_gas = (currentGas.cylinderSize?.toDoubleOrNull() ?: 0.0) * 14.2 * 0.8 /
                (currentGas.numBerners?.toIntOrNull() ?: 1) /
                (currentGas.burnRate?.toDoubleOrNull() ?: 1.0)
        holder.remain.text = remain_gas.toString()

    }

    override fun getItemCount(): Int {
        return gasList.size
    }

    fun setOnGasClickListener(clickListener: onGasClickListener) {
        mListener = clickListener
    }

    interface onGasClickListener {
        fun onGasClick(position: Int)
    }

    private fun deleteGas(position: Int) {
        val currentItem = gasList[position]
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val databaseReference = FirebaseDatabase.getInstance().getReference("Gas")
        val query = databaseReference.orderByChild("gasId").equalTo(currentItem.gasId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnap in snapshot.children) {
                    if (itemSnap.child("userId").value == userId) {
                        itemSnap.ref.removeValue()
                        gasList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        })
    }
}