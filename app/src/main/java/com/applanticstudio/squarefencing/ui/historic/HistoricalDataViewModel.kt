package com.applanticstudio.squarefencing.ui.historic

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.model.Event
import javax.inject.Inject

class HistoricalDataViewModel : ViewModel() {

    @Inject
    lateinit var localDataManager: LocalDataRepository

    fun fetchHistoricalData(): LiveData<List<Event>> {
        return localDataManager.getAllEvents()
    }
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
