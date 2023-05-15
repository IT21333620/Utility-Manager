package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymanager.R
import com.example.utilitymanager.dataClasses.App
import com.example.utilitymanager.adpters.AppAdapter

class AllAppScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appList: ArrayList<App>
    private lateinit var appAdapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_app_screen)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        appList = ArrayList()

        appList.add(App(R.drawable.img_youtube_logo, "You Tube","R.drawable.img_youtube_logo"))
        appList.add(App(R.drawable.img_tiktok, "Tik Tok","R.drawable.img_tiktok"))
        appList.add(App(R.drawable.oip_removebg_preview,"Instagram","R.drawable.oip_removebg_preview"))
        appList.add(App(R.drawable.img_whatsapp,"Whatsapp","R.drawable.img_whatsapp"))
        appList.add(App(R.drawable.img_fb_logo, "Facebook","R.drawable.img_fb_logo"))
        appList.add(App(R.drawable.img_chrome, "Other","R.drawable.img_chrome"))

        appAdapter = AppAdapter(appList)
        recyclerView.adapter = appAdapter

        appAdapter.onItemClick = {
            val  intent = Intent(this , Add_screen_time::class.java)
            intent.putExtra("app", it)
            startActivity(intent)
        }

    }
}