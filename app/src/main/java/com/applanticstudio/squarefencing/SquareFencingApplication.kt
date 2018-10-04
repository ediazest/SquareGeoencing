package com.applanticstudio.squarefencing

import android.app.Application
import android.arch.persistence.room.Room
import com.applanticstudio.squarefencing.data.local.AppDatabase

class SquareFencingApplication : Application() {

    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(this,
                AppDatabase::class.java,
                "location-app-database")
                .build()
    }
}