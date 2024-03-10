package fr.lucas.weatherapp.dependency_injection

import fr.lucas.weatherapp.network.repository.weatherDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { weatherDataRepository(weatherAPI = get()) }
}
