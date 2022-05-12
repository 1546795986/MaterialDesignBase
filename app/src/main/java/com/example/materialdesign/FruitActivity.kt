package com.example.materialdesign

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FruitActivity:AppCompatActivity() {
    companion object{
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_fruit)
        Log.d("DFSDFSDF","SDFDSFFDS")
        val fruitImageView:ImageView = findViewById(R.id.fruitImageView)
        val fruitContentText:TextView = findViewById(R.id.fruitContentText)
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImaged = intent.getIntExtra(FRUIT_IMAGE_ID,0)
        Log.d("Sddsfaf",fruitName+"---"+fruitImaged)
        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title =fruitName
        Glide.with(this).load(fruitImaged).into(fruitImageView)
        fruitContentText.text = generateFruitContent(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return true
    }
    private fun generateFruitContent(fruitName: String): String? {
        return fruitName.repeat(500)
    }
}