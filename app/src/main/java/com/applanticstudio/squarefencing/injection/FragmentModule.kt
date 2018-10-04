package com.applanticstudio.squarefencing.injection

import com.applanticstudio.squarefencing.ui.historic.HistoricalDataFragment
import com.applanticstudio.squarefencing.ui.simulation.SimulationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun historicalDataFragment(): HistoricalDataFragment

    @ContributesAndroidInjector
    internal abstract fun simulationFragment(): SimulationFragment
}