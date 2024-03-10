package fr.lucas.weatherapp.fragments.location


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.lucas.weatherapp.data.RemoteLocation
import fr.lucas.weatherapp.network.repository.weatherDataRepository
import kotlinx.coroutines.launch


class LocationViewModel(private val weatherDataRepository: weatherDataRepository) : ViewModel() {

    private val _searchResult = MutableLiveData<SearchResultDataState>()
    val searchResult: LiveData<SearchResultDataState> get() = _searchResult


    fun searchLocation(query: String) {
        viewModelScope.launch {
            emitSearchResultUiState(isLoading = true)
            val searchResult = weatherDataRepository.searchLocation(query)
            if(searchResult.isNullOrEmpty()) {
                emitSearchResultUiState(error = "Location not found, please try again")
            } else {
                emitSearchResultUiState(locations = searchResult)
            }
        }
    }


    private fun emitSearchResultUiState(
        isLoading: Boolean = false,
        locations: List<RemoteLocation>? = null,
        error: String? = null
    ) {
        val searchResultDataState = SearchResultDataState(isLoading, locations, error)
        _searchResult.value = searchResultDataState
    }


    data class SearchResultDataState(
        var isLoading: Boolean,
        val locations: List<RemoteLocation>?,
        val error: String?
    )
}