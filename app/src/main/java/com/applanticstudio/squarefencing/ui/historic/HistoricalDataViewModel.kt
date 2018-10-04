package com.applanticstudio.squarefencing.ui.historic

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.applanticstudio.squarefencing.SquareFencingApplication
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.model.Event

class HistoricalDataViewModel(application: Application) : AndroidViewModel(application) {

    private val localDataManager = LocalDataRepository.getInstance((application as SquareFencingApplication).appDatabase)

    fun fetchHistoricalData(): LiveData<List<Event>> {
        return localDataManager.getAllEvents()
    }
}