package com.applanticstudio.squarefencing.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.applanticstudio.squarefencing.data.model.Converters
import com.applanticstudio.squarefencing.data.model.Event
import com.applanticstudio.squarefencing.data.model.EventDAO


@Database(entities = [(Event::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDAO
}