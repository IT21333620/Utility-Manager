package com.example.utilitymanager.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R

class AppAdapter(private val appList:ArrayList<App>)
    : RecyclerView.Adapter<AppAdapter.AppViewHolder>(){

    var onItemClick : ((App) -> Unit)? = null

    class AppViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.cover)
        val textView : TextView = itemView.findViewById(R.id.textViewapp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_item, parent, false)
        return AppViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = appList[position]
        holder.imageView.setImageResource(app.image)
        holder.textView.text = app.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(app)
        }

    }
}