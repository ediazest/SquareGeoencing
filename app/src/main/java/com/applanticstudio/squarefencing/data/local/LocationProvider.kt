package com.applanticstudio.squarefencing.data.local

import com.applanticstudio.squarefencing.data.model.Point
import io.reactivex.Observable

interface LocationProvider {
    fun subscribeToLocationUpdates(): Observable<Point>
}