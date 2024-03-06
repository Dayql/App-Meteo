package fr.lucas.weatherapp.fragments.home

import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import fr.lucas.weatherapp.data.CurrentLocation
import fr.lucas.weatherapp.network.repository.weatherDataRepository
import kotlinx.coroutines.launch

class homeViewModel(private val weatherDataRepository: weatherDataRepository) : ViewModel() {

    private val _currentLocation = MutableLiveData<CurrentLocationDataState>()
    val currentLocation: LiveData<CurrentLocationDataState> get() = _currentLocation

    fun getCurrentLocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        geocoder: Geocoder
    ) {
        viewModelScope.launch {
            emitCurrentLocationUiState(isLoading = true)
            weatherDataRepository.getCurrentLocation(
                fusedLocationProviderClient = fusedLocationProviderClient,
                onSuccess = { currentLocation ->
                    updateAddressText(currentLocation, geocoder)
                },
                onFailure = {
                    emitCurrentLocationUiState(error = "Unable to fetch current location")
                }
            )
        }
    }


    private fun updateAddressText (currentLocation: CurrentLocation, geocoder: Geocoder) {
        viewModelScope.launch {
            val location = weatherDataRepository.updateAddressText(currentLocation,geocoder)
            emitCurrentLocationUiState(currentLocation = location)
        }
    }



    private fun emitCurrentLocationUiState(
        isLoading: Boolean = false,
        currentLocation: CurrentLocation? = null,
        error: String? = null
    ) {
        val CurrentLocationDataState = CurrentLocationDataState(isLoading, currentLocation, error)
        _currentLocation.value = CurrentLocationDataState
    }


    data class CurrentLocationDataState(
        val isLoading: Boolean,
        val CurrentLocation: CurrentLocation?,
        val error: String?
    )


}