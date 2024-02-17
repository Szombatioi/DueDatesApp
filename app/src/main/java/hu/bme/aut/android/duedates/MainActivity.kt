package hu.bme.aut.android.duedates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.duedates.database.DueDatabase
import hu.bme.aut.android.duedates.database.DueEntity
import hu.bme.aut.android.duedates.databinding.ActivityMainBinding
import hu.bme.aut.android.duedates.recyclerview.ElemAdapter
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), ElemAdapter.ElemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DueDatabase
    private lateinit var adapter: ElemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = DueDatabase.getDatabase(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            startActivity(Intent(this, AddDate::class.java))
        }

        initRecyclerView()

        //Menu with deleteAll icon
    }

    private fun initRecyclerView() {
        adapter = ElemAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.DueEntityDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: DueEntity) {
        thread{
            database.DueEntityDao().update(item)
        }
    }
}