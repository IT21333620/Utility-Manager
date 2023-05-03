package com.example.utilitymanager.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R



class CardAdepter (private val itemList: ArrayList<Item>)
    :RecyclerView.Adapter<CardAdepter.CardViewHolder>(){

    var onItemClick : ((Item) -> Unit)? = null
    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_electro,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageView.setImageResource(item.image)
        holder.textView.text = item.title

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(item)
        }
    }


}