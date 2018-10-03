package com.applanticstudio.squarefencing.data.local

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.applanticstudio.squarefencing.data.model.Event
import com.applanticstudio.squarefencing.data.model.Point
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class LocalDataManagerTest {

    private lateinit var localDataManager: LocalDataManager

    @Before
    fun setUp() {
        localDataManager = LocalDataManager(InstrumentationRegistry.getTargetContext())
    }

    @After
    fun tearDown() {
        localDataManager.dropAllValues()
    }

    @Test
    fun shouldInsertEvent() {

        assert(localDataManager.getAllEvents().isEmpty())

        val event1 = Event(UUID.randomUUID().toString(), Date(),
                false, "RegionA",
                Point(51.751638, -1.260650))

        val event2 = Event(UUID.randomUUID().toString(), Date(),
                false, "RegionB",
                Point(51.751638, -1.260650))

        localDataManager.insertEvent(event1)

        Assert.assertEquals(true,
                localDataManager.getAllEvents().first().identifier == event1.identifier)
        Assert.assertEquals(true,
                localDataManager.getAllEvents().size == 1)

        localDataManager.insertEvent(event2)
        Assert.assertEquals(true,
                localDataManager.getAllEvents().size == 2)
    }
}