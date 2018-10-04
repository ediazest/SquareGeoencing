package com.applanticstudio.squarefencing.injection

import android.app.Application
import android.arch.persistence.room.Room
import com.applanticstudio.squarefencing.data.local.AppDatabase
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.local.LocationManager
import com.applanticstudio.squarefencing.data.model.Point
import com.applanticstudio.squarefencing.data.model.Region
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    fun appDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app,
                AppDatabase::class.java,
                "location-app-database")
                .build()
    }

    @Provides
    @Singleton
    fun localDataRepository(): LocalDataRepository {
        return LocalDataRepository()
    }

    @Provides
    @Singleton
    fun locationManager(): LocationManager {
        val regionManager = LocationManager()


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

        return regionManager
    }
}