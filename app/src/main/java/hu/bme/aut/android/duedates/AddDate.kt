package hu.bme.aut.android.duedates

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Database
import hu.bme.aut.android.duedates.database.DueDatabase
import hu.bme.aut.android.duedates.database.DueEntity
import hu.bme.aut.android.duedates.databinding.ActivityAddDateBinding
import java.time.LocalDate
import java.util.Date
import kotlin.concurrent.thread

class AddDate : AppCompatActivity() {

    private lateinit var binding: ActivityAddDateBinding
    private lateinit var database: DueDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_date)

        binding = ActivityAddDateBinding.inflate(layoutInflater)
        database = DueDatabase.getDatabase(applicationContext)

        

        binding.addBtn.setOnClickListener {
            Log.i("Button", "Button pressed")
            if(!valid()) return@setOnClickListener
            Log.i("Button", "Button passed")
            thread{
                val dateText = binding.dueDateText.text.toString().split('.')
                database.DueEntityDao().save(DueEntity(
                    className = binding.classNameText.text.toString(),
                    subjectName = binding.classNameText.text.toString(),
                    dueDate = Date(dateText[0].toInt(), dateText[1].toInt(), dateText[2].toInt())
                ))
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }
    }

    private fun valid(): Boolean{
        if(binding.classNameText.text.isEmpty()){
            binding.classNameText.requestFocus()
            binding.classNameText.error = "Please fill this"
            return false
        }
        else if(binding.descriptionText.text.isEmpty()){
            binding.descriptionText.requestFocus()
            binding.descriptionText.error = "Please fill this"
            return false
        }
        else if(binding.dueDateText.text.isEmpty()){
            binding.descriptionText.requestFocus()
            binding.descriptionText.error = "Please fill this"
            return false
        }
        return true
    }
}