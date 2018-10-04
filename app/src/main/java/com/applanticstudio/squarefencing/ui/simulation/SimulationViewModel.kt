package com.applanticstudio.squarefencing.ui.simulation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.applanticstudio.squarefencing.data.local.LocationManager
import com.applanticstudio.squarefencing.data.local.LocationProviderMock
import com.applanticstudio.squarefencing.data.model.Point
import com.applanticstudio.squarefencing.data.model.Region
import io.reactivex.Observable

class SimulationViewModel(application: Application) : AndroidViewModel(application) {

    private val regionManager = LocationManager()
    private val locationProvider = LocationProviderMock()

    init {

        val regionA = Region("Region A",
                listOf(Point(51.750890, -1.261411),
                        Point(51.750578, -1.260736),
                        Point(51.751419, -1.259915),
                        Point(51.751638, -1.260650)))

        val regionB = Region("Region B",
                listOf(Point(51.751547, -1.260424),
                        Point(51.751454, -1.260054),
                        Point(51.751106, -1.260446),
                        Point(51.751189, -1.260768)))

        regionManager.includeRegion(regionA)
        regionManager.includeRegion(regionB)
    }

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
                        val isInside = regionManager.isInsideAnyMonitoredRegion(it).isEmpty()
                        if (!previous && isInside) {
                            observer.onNext("Tim enters zone\n")
                        } else if (previous && !isInside) {
                            observer.onNext("Tim leaves zone\n")
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