package com.applanticstudio.squarefencing.injection

import android.app.Application
import com.applanticstudio.squarefencing.SquareGeoApplication
import com.applanticstudio.squarefencing.data.local.AppDatabase
import com.applanticstudio.squarefencing.data.local.LocalDataRepository
import com.applanticstudio.squarefencing.data.local.LocationManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityModule::class, FragmentModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: SquareGeoApplication)

    fun appDatabase(): AppDatabase

    fun localDataRepository(): LocalDataRepository

    fun locationManager(): LocationManager
}