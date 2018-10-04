package com.applanticstudio.squarefencing.data.local

import com.applanticstudio.squarefencing.data.model.Point
import com.applanticstudio.squarefencing.data.model.Region

class LocationManager {

    private var regions: MutableList<Region> = arrayListOf()

    fun includeRegion(regionToMonitor: Region) {
        regions.add(regionToMonitor)
    }

    fun isMonitoringAnyRegion(): Boolean {
        return regions.isNotEmpty()
    }

    fun isInsideAnyMonitoredRegion(point: Point): List<Region> {

        return regions.filter {
            isPointInPolygon(point, it.vertices)
        }
    }

    //Following https://stackoverflow.com/a/26030795 response for implementing Ray Cast algorithm
    private fun isPointInPolygon(tap: Point, vertices: List<Point>): Boolean {
        var intersectCount = 0
        for (j in 0 until vertices.size - 1) {
            if (rayCastIntersect(tap, vertices[j], vertices[j + 1])) {
                intersectCount++
            }
        }

        return intersectCount % 2 == 1 // odd = inside, even = outside;
    }

    private fun rayCastIntersect(tap: Point, vertA: Point, vertB: Point): Boolean {

        val aY = vertA.latitude
        val bY = vertB.latitude
        val aX = vertA.longitude
        val bX = vertB.longitude
        val pY = tap.latitude
        val pX = tap.longitude

        if (aY > pY && bY > pY || aY < pY && bY < pY
                || aX < pX && bX < pX) {
            return false // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        val m = (aY - bY) / (aX - bX) // Rise over run
        val bee = -aX * m + aY // y = mx + b
        val x = (pY - bee) / m // algebra is neat!

        return x > pX
    }
}


