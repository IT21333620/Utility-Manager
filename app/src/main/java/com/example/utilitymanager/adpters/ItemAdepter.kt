package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.utilitymanager.R
import com.example.utilitymanager.models.ElectroItemModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ItemAdepter (private val itemList: ArrayList<ElectroItemModel>)
    : RecyclerView.Adapter<ItemAdepter.ViewHolder>(){

    private lateinit var mLinstener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mLinstener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_electro,parent,false)
        return ViewHolder(itemView,mLinstener)
    }

    override fun onBindViewHolder(holder: ItemAdepter.ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.title.text = currentItem.title
        Glide.with(holder.itemView.context)
            .load(currentItem.itemUrl)
            .into(holder.image)
        holder.btnUpdate.setOnClickListener {
            mLinstener.onItemClick(position)
        }
        holder.btnDelete.setOnClickListener {
            deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder (itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.elecTitle)
        val image: ImageView = itemView.findViewById(R.id.imgItemElec)
        val btnUpdate: FloatingActionButton = itemView.findViewById(R.id.btnUpdateItem)
        val btnDelete: Button = itemView.findViewById(R.id.btnElecDelete)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

    private fun deleteItem(position: Int) {
        val currentItem = itemList[position]
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""

        val databaseReference = FirebaseDatabase.getInstance().getReference("electric_item")
        val query = databaseReference.orderByChild("itemId").equalTo(currentItem.itemId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnap in snapshot.children) {
                    if (itemSnap.child("userId").value == userId) {
                        itemSnap.ref.removeValue()
                        itemList.removeAt(position)
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