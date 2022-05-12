package com.example.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple), Fruit("Banana",
        R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon",
        R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape",
        R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry",
        R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango",
        R.drawable.mango))
    val fruitList = ArrayList<Fruit>()
    lateinit var drawerLayout:DrawerLayout
    lateinit var navView:NavigationView
    lateinit var fab:FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh:SwipeRefreshLayout
    lateinit var adapter:FruitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        initFruits()
        //spanCount是列数
        val layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager  = layoutManager
        adapter = FruitAdapter(this,fruitList)
        recyclerView.adapter = adapter
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        fab = findViewById(R.id.fab)
        swipeRefresh =findViewById(R.id.swipeRefresh)
        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_launcher_background)

        }
        /*首先调用了NavigationView的setCheckedItem()方法将
Call菜单项设置为默认选中。接着调用了setNavigationItemSelectedListener()方法
来设置一个菜单项选中事件的监听器
        * */
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
        fab.setOnClickListener{view ->
//            Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show()
//            Snackbar的用法也非常简单，它和Toast是基本相似的，只不过可以额外增加一个按钮的点击
//事件
            Snackbar.make(view,"data deleted",Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this,"Data restores",Toast.LENGTH_LONG).show()
                }
                .show()
        }

        //SwipeRefreshLayout的
        //setColorSchemeResources()方法来设置下拉刷新进度条的颜色，这里我们就使用主题中
        //的colorPrimary作为进度条的颜色了。接着调用setOnRefreshListener()方法来设置一
        //个下拉刷新的监听器
        swipeRefresh.setColorSchemeResources(R.color.black)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread{
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50){
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //actionBar上面的

            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)

            R.id.backup -> Toast.makeText(this, "You clicked Backup",
                Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
        }

        return true

    }
}