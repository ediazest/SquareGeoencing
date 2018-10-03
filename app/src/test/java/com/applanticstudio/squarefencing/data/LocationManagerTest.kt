package com.applanticstudio.squarefencing.data

import org.junit.Before
import org.junit.Test

class LocationManagerTest {

    lateinit var regionManager: LocationManager

    @Before
    fun setUp() {
        regionManager = LocationManager()
    }

    @Test
    fun isMonitoringAnyRegion() {

        assert(!regionManager.isMonitoringAnyRegion(),
                { "Error. Monitoring areas should be empty" })

        val regionA = Region("Region A",
                listOf(Point(51.751638, -1.260650),
                        Point(51.751419, -1.259915),
                        Point(51.750578, -1.260736),
                        Point(51.750890, -1.261411)))

        regionManager.includeRegion(regionA)

        assert(regionManager.isMonitoringAnyRegion(),
                { "Error. Monitoring areas should not be empty" })
    }

    @Test
    fun isInsideAnyMonitoredRegion() {

        val regionA = Region("Region A",
                listOf(Point(51.750890, -1.261411),
                        Point(51.750578, -1.260736),
                        Point(51.751638, -1.260650),
                        Point(51.751419, -1.259915)))

        val regionB = Region("Region B",
                listOf(Point(51.751547, -1.260424),
                        Point(51.751454, -1.260054),
                        Point(51.751106, -1.260446),
                        Point(51.751189, -1.260768)))

        regionManager.includeRegion(regionA)
        regionManager.includeRegion(regionB)

        val insidePoint = Point(51.751454, -1.260054)
        assert(regionManager.isInsideAnyMonitoredRegion(insidePoint),
                { "Error. Point should be inside monitored area" })

        val outsidePoint = Point(50.751454, -1.280054)
        assert(!regionManager.isInsideAnyMonitoredRegion(outsidePoint),
                { "Error. Point should not be inside monitored area" })

    }
}