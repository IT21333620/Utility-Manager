package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.models.waterlist

class WaterAdapter(private val cardhistorylist: ArrayList<waterlist>) : RecyclerView.Adapter<WaterAdapter.MyViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_historylist,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardhistorylist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = cardhistorylist[position]

        holder.watId.text = currentitem.watId
        holder.week.text = currentitem.week
        holder.drinking.text = currentitem.drinking
        holder.washing.text = currentitem.washing
        holder.showering.text = currentitem.showering
        holder.gardening.text = currentitem.gardening
        holder.other.text = currentitem.other
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val watId : TextView = itemView.findViewById(R.id.tvWatId)
        val week : TextView = itemView.findViewById(R.id.tvWeek)
        val washing : TextView = itemView.findViewById(R.id.tvWashing)
        val showering : TextView = itemView.findViewById(R.id.tvShowering)
        val drinking : TextView = itemView.findViewById(R.id.tvDrinking)
        val gardening : TextView = itemView.findViewById(R.id.tvGardening)
        val other : TextView = itemView.findViewById(R.id.tvOther)

    }
}