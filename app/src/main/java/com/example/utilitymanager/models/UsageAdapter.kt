package com.example.utilitymanager.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R

class UsageAdapter(private val usageList: ArrayList<ScreenModel>) : RecyclerView.Adapter<UsageAdapter.UsageViewHolder>(){

    class UsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val appName : TextView = itemView.findViewById(R.id.textViewapp)
        val screenTime : TextView = itemView.findViewById(R.id.screenTime)
        val device : TextView = itemView.findViewById(R.id.device)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsageViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.usage_item,
            parent,false)
        return UsageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usageList.size
    }

    override fun onBindViewHolder(holder: UsageAdapter.UsageViewHolder, position: Int) {

        val app = usageList[position]
        holder.appName.text = app.appName
        holder.screenTime.text = app.screTime
        holder.device.text = app.device

    }



}