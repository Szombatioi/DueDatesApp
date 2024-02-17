package hu.bme.aut.android.duedates.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "dueentity")
data class DueEntity(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name="class") var className: String,
    @ColumnInfo(name="subject") var subjectName: String,
    @ColumnInfo(name="date") var dueDate: Date
){}
