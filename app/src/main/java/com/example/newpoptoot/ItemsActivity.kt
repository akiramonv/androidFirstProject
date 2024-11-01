package com.example.newpoptoot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemsList:RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()



        items.add((Item(1, "uwu", "Soft-Hard Lora","Поп-рок-метал-джаз-кантри айдал музыкант Лора Лиларо","Этот персонаж погружает тебя в музыкальный мир, где стили сливаются в уникальный звук.",10 )))
        items.add((Item(2, "ping", "Dududu","Нападение пенгвинов из далеких недр южной Антарктиды","Невероятно храбрые пингвины борются за свое место под солнцем в бескрайних ледяных просторах.",7 )))
        items.add((Item(3, "fly_to_moon", "War Stars","Однажды в далекой-далекой галактике","Эпическая история межзвездных войн, полная отваги и приключений среди звезд.", 49)))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)
    }
}