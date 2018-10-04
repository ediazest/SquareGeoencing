package com.applanticstudio.squarefencing.injection

import android.arch.lifecycle.ViewModel
import com.applanticstudio.squarefencing.ui.historic.HistoricalDataViewModel
import com.applanticstudio.squarefencing.ui.simulation.SimulationViewModel
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindHistoricalDataViewModel(viewModel: HistoricalDataViewModel): ViewModel

    @Binds
    abstract fun bindSimulationViewModel(viewModel: SimulationViewModel): ViewModel
}