package hu.bme.aut.android.duedates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Database
import hu.bme.aut.android.duedates.database.DueDatabase
import hu.bme.aut.android.duedates.database.DueEntity
import hu.bme.aut.android.duedates.databinding.ActivityAddDateBinding
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
            if(!valid()) return@setOnClickListener

            thread{
                database.DueEntityDao().save(DueEntity(
                    className = binding.classNameText.text.toString(),
                    subjectName = binding.classNameText.text.toString(),
                    dueDate = Date()
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