package hu.bme.aut.android.duedates.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DueEntityDao {
    @Query("""SELECT * FROM dueentity""")
    fun getAll(): List<DueEntity>

    @Insert
    fun save(d: DueEntity)

    @Delete
    fun delete(d: DueEntity)

    @Query("""DELETE FROM dueentity""")
    fun deleteAll()

    @Update
    fun update(d: DueEntity)
}