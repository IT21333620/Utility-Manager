package com.example.utilitymanager.models

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.databinding.ActivityRecyclierViewBinding

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

        itemList.add(Item(R.drawable.img_light, "Light Bulb"))
        itemList.add(Item(R.drawable.img_fan, "Fan"))
        itemList.add(Item(R.drawable.img_fridge, "Fridge"))
        itemList.add(Item(R.drawable.img_tv, "TV"))
        itemList.add(Item(R.drawable.img_ac, "A/C"))
        itemList.add(Item(R.drawable.img_washing_machine, "Washing Machine"))
        itemList.add(Item(R.drawable.img_rice_cooker, "Rice Cooker"))
        itemList.add(Item(R.drawable.img_iron, "Iron"))
        itemList.add(Item(R.drawable.img_kettle, "Kettle"))
        itemList.add(Item(R.drawable.img_hot_plate, "Hot Plate"))

        cardAdepter = CardAdepter(itemList)
        recyclerView.adapter = cardAdepter

        cardAdepter.onItemClick = {
            val intent = Intent(this,details_electricity::class.java)
            intent.putExtra("item",it)
            startActivity(intent)
        }
    }

}