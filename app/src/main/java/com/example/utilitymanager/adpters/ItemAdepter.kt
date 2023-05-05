package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.utilitymanager.R
import com.example.utilitymanager.models.ElectroItemModel

class ItemAdepter (private val itemList: ArrayList<ElectroItemModel>)
    : RecyclerView.Adapter<ItemAdepter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_electro,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdepter.ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.title.text = currentItem.title
        Glide.with(holder.itemView.context)
            .load(currentItem.itemUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.elecTitle)
        val image: ImageView = itemView.findViewById(R.id.imgItemElec)

    }


}