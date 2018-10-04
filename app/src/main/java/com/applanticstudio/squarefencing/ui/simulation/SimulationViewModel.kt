package com.applanticstudio.squarefencing.ui.simulation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.local.LocationManager
import com.applanticstudio.squarefencing.data.local.LocationProviderMock
import com.applanticstudio.squarefencing.data.model.Event
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class SimulationViewModel(application: Application) : AndroidViewModel(application) {
    private val locationProvider = LocationProviderMock()
    private val user = "Tim"

    @Inject
    lateinit var regionManager: LocationManager

    @Inject
    lateinit var localDataManager: LocalDataRepository

    fun startSimulationProcess(): Observable<String> {
        return Observable.create<String> { observer ->
            observer.onNext("")
            observer.onNext("*******************\n")
            observer.onNext("Starting simulation\n")
            observer.onNext("*******************\n")

            var previous = false
            locationProvider.subscribeToLocationUpdates()
                    .subscribe({
                        observer.onNext("New location registered: ${it.latitude},${it.longitude}\n")
                        val regions = regionManager.isInsideAnyMonitoredRegion(it)
                        val isInside = !regions.isEmpty()
                        if (!previous && isInside) {
                            for (region in regions) {
                                observer.onNext("$user enters zone ${region.identifier}\n")
                                localDataManager.insertEvent(Event(UUID.randomUUID().toString(), Date(),
                                        false, region.identifier, it, user))
                            }
                        } else if (previous && !isInside) {
                            for (region in regions) {
                                observer.onNext("$user leaves zone ${region.identifier}\n")
                                localDataManager.insertEvent(Event(UUID.randomUUID().toString(), Date(),
                                        true, region.identifier, it, user))
                            }
                        }
                        previous = isInside
                    }, {
                        observer.onError(it)
                    }, {
                        observer.onComplete()
                    })
        }
    }
}