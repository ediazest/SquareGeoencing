package com.applanticstudio.squarefencing.ui.historic

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import com.applanticstudio.squarefencing.data.local.AppDatabase
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.model.Event

class HistoricalDataViewModel(application: Application) : AndroidViewModel(application) {

    private val appDatabase = Room.databaseBuilder(application,
            AppDatabase::class.java,
            "location-app-database")
            .build()
    private val localDataManager = LocalDataRepository.getInstance(appDatabase)

    fun fetchHistoricalData(): LiveData<List<Event>> {
        return localDataManager.getAllEvents()
    }
}