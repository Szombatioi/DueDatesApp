package hu.bme.aut.android.duedates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.bme.aut.android.duedates.database.DueDatabase
import hu.bme.aut.android.duedates.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DueDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = DueDatabase.getDatabase(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get recyclerview
        //fill with data

        //layout: FAB
        //RecyclerView for elements
        //Menu with deleteAll icon
    }
}