package hu.bme.aut.android.duedates.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[DueEntity::class], version=1)
@TypeConverters(Converters::class)
abstract class DueDatabase : RoomDatabase() {
    abstract fun DueEntityDao(): DueEntityDao
    companion object {
        fun getDatabase(applicationContext: Context): DueDatabase {
            return Room.databaseBuilder(
                applicationContext,
                DueDatabase::class.java,
                "dueDB"
            ).build();
        }
    }

}