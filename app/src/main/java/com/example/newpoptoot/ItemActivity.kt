package com.example.newpoptoot

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class ItemActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemActivity> {
        override fun createFromParcel(parcel: Parcel): ItemActivity {
            return ItemActivity(parcel)
        }

        override fun newArray(size: Int): Array<ItemActivity?> {
            return arrayOfNulls(size)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title: TextView = findViewById(R.id.item_list_title_one)
        val text: TextView = findViewById(R.id.item_list_text)
        val image: ImageView = findViewById(R.id.item_list_image_one)
        val imageId: Int = intent.getIntExtra("itemImageId", 0)
        val buttonPrev:Button = findViewById(R.id.button_prev)
        val buttonURL = findViewById<Button>(R.id.button_know)


        buttonURL.setOnClickListener {
            val url = intent.getStringExtra("itemURL")
            val uri:Uri=Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }

        buttonPrev.setOnClickListener{
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
        image.setImageResource(imageId)
    }
}