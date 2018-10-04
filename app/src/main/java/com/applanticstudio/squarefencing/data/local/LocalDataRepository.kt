package com.applanticstudio.squarefencing.data.local

import android.arch.lifecycle.LiveData
import com.applanticstudio.squarefencing.data.model.Event
import javax.inject.Inject


class LocalDataRepository {

    @Inject
    lateinit var appDatabase: AppDatabase

    fun insertEvent(event: Event) {
        appDatabase.eventDao().insert(event)
    }

    fun getAllEvents(): LiveData<List<Event>> {
        return appDatabase.eventDao().getAll()
    }

    fun dropAllValues() {
        appDatabase.eventDao().removeAll()
    }

}