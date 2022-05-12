package com.example.materialdesign

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FruitAdapter(val context:Context,val fruitList:List<Fruit>):
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        //找到控件
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.ViewHolder {
        //找到子布局
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            Log.d("DSFSDF","DSFSDF")
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            context.apply {
                startActivity(intent)
                Log.d("DSFSFDSFSDF","FDSFSD")
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: FruitAdapter.ViewHolder, position: Int) {
        //找到每一个的fruit实例
        val fruit = fruitList[position]
        //利用holdr找到控件并绑定数据
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)

    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

}