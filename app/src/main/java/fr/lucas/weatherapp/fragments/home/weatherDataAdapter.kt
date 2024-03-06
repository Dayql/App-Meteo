package fr.lucas.weatherapp.fragments.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.lucas.weatherapp.data.CurrentLocation
import fr.lucas.weatherapp.data.weatherData
import fr.lucas.weatherapp.databinding.ItemContainerCurrentLocationBinding


class weatherDataAdapter (
    private val onLocationClicked: () -> Unit
) : RecyclerView.Adapter<weatherDataAdapter.CurrentLocationViewHolder>() {

    private val weatherData = mutableListOf<weatherData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<weatherData>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
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


