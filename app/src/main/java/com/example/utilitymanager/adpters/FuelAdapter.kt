package com.example.utilitymanager.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.models.FuelModel
import com.example.utilitymanager.R

class FuelAdapter(
    private val weekList: ArrayList<FuelModel>,
    private val onItemClickListener: OnItemClickListener,
    ) : RecyclerView.Adapter<FuelAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fuel_history,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return weekList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentFuel = weekList[position]

        holder.week.text = currentFuel.week
        holder.distance.text = currentFuel.distance
        holder.fuelUsed.text = currentFuel.fuelUsed
        holder.totalCost.text = currentFuel.totalCost

        // Set the click listener for the update button
        holder.btnUpdate.setOnClickListener {
            onItemClickListener.onUpdateClick(currentFuel)
        }

        holder.btnDelete.setOnClickListener {
            onItemClickListener.onDeleteClick(currentFuel)
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val week : TextView = itemView.findViewById(R.id.tvWeek)
        val distance : TextView = itemView.findViewById(R.id.tvDistance)
        val fuelUsed : TextView = itemView.findViewById(R.id.tvUsage)
        val totalCost : TextView = itemView.findViewById(R.id.tvCost)
        val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

    }

    interface OnItemClickListener {
        fun onUpdateClick(fuel: FuelModel)
        fun onDeleteClick(fuel: FuelModel)
    }

}