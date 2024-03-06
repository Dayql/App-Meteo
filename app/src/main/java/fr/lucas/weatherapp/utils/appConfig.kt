package fr.lucas.weatherapp.utils

import android.app.Application
import fr.lucas.weatherapp.dependency_injection.repositoryModule
import fr.lucas.weatherapp.dependency_injection.viewModelModule
import org.koin.core.context.startKoin

class appConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(repositoryModule, viewModelModule))
        }
    }

}

