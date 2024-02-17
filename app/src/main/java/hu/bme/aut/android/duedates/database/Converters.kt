package hu.bme.aut.android.duedates.database

import androidx.room.TypeConverter
import java.util.Date
import kotlin.reflect.KClass

class Converters() {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}