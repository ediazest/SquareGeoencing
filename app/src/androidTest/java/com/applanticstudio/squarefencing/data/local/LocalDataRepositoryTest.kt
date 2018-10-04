package com.applanticstudio.squarefencing.data.local

import android.arch.persistence.room.Room
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
class LocalDataRepositoryTest {

    private lateinit var localDataRepository: LocalDataRepository

    @Before
    fun setUp() {

        val appDatabase = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(),
                AppDatabase::class.java,
                "location-app-database")
                .build()
        localDataRepository = LocalDataRepository.getInstance(appDatabase)
    }

    @After
    fun tearDown() {
        localDataRepository.dropAllValues()
    }

    @Test
    fun shouldInsertEvent() {

        assert(localDataRepository.getAllEvents().value!!.isEmpty())

        val event1 = Event(UUID.randomUUID().toString(), Date(),
                false, "RegionA",
                Point(51.751638, -1.260650), "Tim")

        val event2 = Event(UUID.randomUUID().toString(), Date(),
                false, "RegionB",
                Point(51.751638, -1.260650), "Tim")

        localDataRepository.insertEvent(event1)

        Assert.assertEquals(true,
                localDataRepository.getAllEvents().value!!.first().identifier == event1.identifier)
        Assert.assertEquals(true,
                localDataRepository.getAllEvents().value!!.size == 1)

        localDataRepository.insertEvent(event2)
        Assert.assertEquals(true,
                localDataRepository.getAllEvents().value!!.size == 2)
    }
}