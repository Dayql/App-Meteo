package fr.lucas.weatherapp.utils

import android.app.Application
import fr.lucas.weatherapp.dependency_injection.repositoryModule
import fr.lucas.weatherapp.dependency_injection.serializerModule
import fr.lucas.weatherapp.dependency_injection.storageModule
import fr.lucas.weatherapp.dependency_injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class appConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@appConfig)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    serializerModule,
                    storageModule
                )
            )
        }
    }

}

