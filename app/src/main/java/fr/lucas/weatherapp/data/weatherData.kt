package fr.lucas.weatherapp.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class weatherData

data class CurrentLocation (
    val date: String = getCurrentDate(),
    val location: String = "Choisissez votre position",
    val latitude: Double? = null,
    val longitude: Double? = null
) : weatherData()

data class CurrentWeather(
    val icon: String,
    val temperature: Float,
    val wind: Float,
    val humidity: Int,
    val chanceOfRain: Int,
) : weatherData()

private fun getCurrentDate(): String {
    val currentDate = Date()
    val formatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return "Today, ${formatter.format(currentDate)}"
}