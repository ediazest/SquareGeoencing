package com.applanticstudio.squarefencing.data.local

import android.arch.persistence.room.Room
import android.content.Context
import com.applanticstudio.squarefencing.data.model.Event


class LocalDataManager(context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(context,
            AppDatabase::class.java,
            "location-app-database")
            .build()

    fun insertEvent(event: Event) {
        db.eventDao().insert(event)
    }

    fun getAllEvents(): List<Event> {
        return db.eventDao().getAll()
    }

    fun dropAllValues(){
        db.eventDao().removeAll()
    }

}