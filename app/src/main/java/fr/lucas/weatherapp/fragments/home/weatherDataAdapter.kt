package fr.lucas.weatherapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.lucas.weatherapp.data.CurrentLocation
import fr.lucas.weatherapp.data.weatherData
import fr.lucas.weatherapp.databinding.ItemContainerCurrentLocationBinding


class weatherDataAdapter (
    private val onLocationClicked: () -> Unit
) : RecyclerView.Adapter<weatherDataAdapter.CurrentLocationViewHolder>() {

    private companion object {
        const val INDEX_CURRENT_LOCATION = 0
        const val INDEX_CURRENT_WEATHER = 1
        const val INDEX_FORECAST = 2
    }


    private val weatherData = mutableListOf<weatherData>()



    fun setCurrentLocation(currentLocation: CurrentLocation) {
        if(weatherData.isEmpty()) {
            weatherData.add(INDEX_CURRENT_LOCATION, currentLocation)
            notifyItemChanged(INDEX_CURRENT_LOCATION)
        } else {
            weatherData[INDEX_CURRENT_LOCATION] = currentLocation
            notifyItemChanged(INDEX_CURRENT_LOCATION)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentLocationViewHolder {
        return CurrentLocationViewHolder(
            ItemContainerCurrentLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrentLocationViewHolder, position: Int) {
        holder.bind(weatherData[position] as CurrentLocation)
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class CurrentLocationViewHolder(
        private val binding: ItemContainerCurrentLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentLocation: CurrentLocation) {
            with(binding) {
                textCurrentDate.text = currentLocation.date
                textCurrentLocation.text = currentLocation.location
                imageCurrentLocation.setOnClickListener{ onLocationClicked() }
                textCurrentLocation.setOnClickListener{ onLocationClicked() }

                }

            }
        }

    }


