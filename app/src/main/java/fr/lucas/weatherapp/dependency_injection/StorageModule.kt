package fr.lucas.weatherapp.dependency_injection

import fr.lucas.weatherapp.storage.SharedPreferencesManager
import org.koin.dsl.module

val storageModule = module {
    single { SharedPreferencesManager(context = get(), gson = get())}
}