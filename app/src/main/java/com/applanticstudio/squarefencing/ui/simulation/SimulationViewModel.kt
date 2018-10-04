package com.applanticstudio.squarefencing.ui.simulation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.applanticstudio.squarefencing.SquareFencingApplication
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.local.LocationManager
import com.applanticstudio.squarefencing.data.local.LocationProviderMock
import com.applanticstudio.squarefencing.data.model.Event
import com.applanticstudio.squarefencing.data.model.Point
import com.applanticstudio.squarefencing.data.model.Region
import io.reactivex.Observable
import java.util.*

class SimulationViewModel(application: Application) : AndroidViewModel(application) {

    private val regionManager = LocationManager()
    private val locationProvider = LocationProviderMock()
    private val user = "Tim"
    private val localDataManager = LocalDataRepository((application as SquareFencingApplication).appDatabase)

    init {

        val regionA = Region("Region A",
                listOf(Point(51.750890, -1.261411),
                        Point(51.750578, -1.260736),
                        Point(51.751419, -1.259915),
                        Point(51.751638, -1.260650),
                        Point(51.750890, -1.261411)))

        val regionB = Region("Region B",
                listOf(Point(51.751547, -1.260424),
                        Point(51.751454, -1.260054),
                        Point(51.751106, -1.260446),
                        Point(51.751189, -1.260768),
                        Point(51.751547, -1.260424)))

        regionManager.includeRegion(regionA)
        regionManager.includeRegion(regionB)
    }

    fun startSimulationProcess(): Observable<String> {
        return Observable.create<String> { observer ->
            observer.onNext("")
            observer.onNext("*******************\n")
            observer.onNext("Starting simulation\n")
            observer.onNext("*******************\n")

            var previous: List<Region> = listOf()
            locationProvider.subscribeToLocationUpdates()
                    .subscribe({
                        observer.onNext("New location registered: ${it.latitude},${it.longitude}\n")
                        val regions = regionManager.isInsideAnyMonitoredRegion(it)

                        for (region in previous.minus(regions)) {
                            observer.onNext("$user leaves zone ${region.identifier}\n")
                            localDataManager.insertEvent(Event(UUID.randomUUID().toString(), Date(),
                                    true, region.identifier, it, user))
                        }

                        for (region in regions.minus(previous)) {
                            observer.onNext("$user enters zone ${region.identifier}\n")
                            localDataManager.insertEvent(Event(UUID.randomUUID().toString(), Date(),
                                    false, region.identifier, it, user))
                        }

                        previous = regions

                    }, {
                        observer.onError(it)
                    }, {
                        observer.onComplete()
                    })
        }
    }
}