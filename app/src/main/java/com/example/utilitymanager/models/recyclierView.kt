package com.example.utilitymanager.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.adpters.CardAdepter

class recyclierView : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemList: ArrayList<Item>
    private lateinit var cardAdepter: CardAdepter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclier_view)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        itemList = ArrayList()

        itemList.add(Item(R.drawable.img_light,6 ,"Light Bulb","R.drawable.img_light"))
        itemList.add(Item(R.drawable.img_fan,4, "Fan","R.drawable.img_fan"))
        itemList.add(Item(R.drawable.img_fridge, 5,"Fridge","img_fridge"))
        itemList.add(Item(R.drawable.img_tv, 4,"TV","img_tv"))
        itemList.add(Item(R.drawable.img_ac, 5,"A/C","img_ac"))
        itemList.add(Item(R.drawable.img_washing_machine, 4,"Washing Machine","img_washing_machine"))
        itemList.add(Item(R.drawable.img_rice_cooker, 4,"Rice Cooker","img_rice_cooker"))
        itemList.add(Item(R.drawable.img_iron, 7,"Iron","img_iron"))
        itemList.add(Item(R.drawable.img_kettle, 6,"Kettle","img_kettle"))
        itemList.add(Item(R.drawable.img_hot_plate, 8,"Hot Plate","img_hot_plate"))

        cardAdepter = CardAdepter(itemList)
        recyclerView.adapter = cardAdepter

        cardAdepter.onItemClick = {
            val intent = Intent(this,details_electricity::class.java)
            intent.putExtra("item",it)
            startActivity(intent)
        }
    }

}