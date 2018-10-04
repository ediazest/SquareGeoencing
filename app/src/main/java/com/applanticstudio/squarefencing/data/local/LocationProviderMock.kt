package com.applanticstudio.squarefencing.data.local

import com.applanticstudio.squarefencing.data.model.Point
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.*

class LocationProviderMock : LocationProvider {

    private val positions = listOf(Point(51.751883, -1.259958),
            Point(51.751710, -1.260049),
            Point(51.751388, -1.260296),
            Point(51.751255, -1.260473),
            Point(51.750961, -1.260792),
            Point(51.750841, -1.260894),
            Point(51.750483, -1.261064),
            Point(51.750174, -1.261171))

    override fun subscribeToLocationUpdates(): Observable<Point> {
        return Observable.create<Point> {
            for ((index, position) in positions.withIndex()) {
                publishNewLocation(it, index, position)
            }
        }
    }

    private fun publishNewLocation(emitter: ObservableEmitter<Point>, index: Int, location: Point) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                emitter.onNext(location)
                if (index == positions.size - 1) {
                    emitter.onComplete()
                }
            }
        }, (1000 * index).toLong())
    }
}