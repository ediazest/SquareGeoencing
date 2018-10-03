package com.applanticstudio.squarefencing.data.model

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toPoint(point: String?): Point? {
        return if (point == null) {
            null
        } else {
            val coordinates = point.split("#")
            Point(coordinates[0].toDouble(), coordinates[1].toDouble())
        }
    }

    @TypeConverter
    fun toLocationString(point: Point?): String {
        return if (point == null) "" else "${point.latitude}#${point.longitude}"
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}