package fr.lucas.weatherapp.dependency_injection

import fr.lucas.weatherapp.fragments.home.homeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { homeViewModel(weatherDataRepository = get()) }
}
