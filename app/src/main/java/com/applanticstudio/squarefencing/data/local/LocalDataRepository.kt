package com.applanticstudio.squarefencing.data.local

import android.arch.lifecycle.LiveData
import com.applanticstudio.squarefencing.data.model.Event


class LocalDataRepository(private val appDatabase: AppDatabase) {

    companion object {

        private var sInstance: LocalDataRepository? = null

        fun getInstance(database: AppDatabase): LocalDataRepository {
            if (sInstance == null) {
                synchronized(LocalDataRepository::class.java) {
                    if (sInstance == null) {
                        sInstance = LocalDataRepository(database)
                    }
                }
            }
            return sInstance!!
        }

    }

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