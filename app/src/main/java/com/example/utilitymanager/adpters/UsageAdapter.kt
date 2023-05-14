package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.dataClasses.Usage

class UsageAdapter(private val usageList: ArrayList<Usage>) : RecyclerView.Adapter<UsageAdapter.UsageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsageViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.usage_item,
        parent,false)
        return UsageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usageList.size
    }

    override fun onBindViewHolder(holder: UsageViewHolder, position: Int) {

        val currentitem = usageList[position]

        holder.screenTime.text = currentitem.screTime
        holder.device.text = currentitem.device

    }


    class UsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val screenTime : TextView = itemView.findViewById(R.id.screenTime)
        val device : TextView = itemView.findViewById(R.id.device)


    }

}