package com.durini.frontendavanzado.database

import com.durini.frontendavanzado.entities.Place
import com.durini.frontendavanzado.entities.PlaceType
import java.util.*

object Database {

    fun randomId() = UUID.randomUUID().toString()

    private val places  = mutableListOf(
        Place(id = randomId(), name = "Ookii", type = PlaceType.RESTAURANT),
        Place(id = randomId(), name = "La Playa", type = PlaceType.BAR),
        Place(id = randomId(), name = "Oakland Mall", type = PlaceType.SHOPPING_MALL),
        Place(id = randomId(), name = "Miraflores", type = PlaceType.SHOPPING_MALL),
        Place(id = randomId(), name = "Astro", type = PlaceType.BAR),
        Place(id = randomId(), name = "Arkadia", type = PlaceType.SHOPPING_MALL),
        Place(id = randomId(), name = "Pecado", type = PlaceType.BAR),
        Place(id = randomId(), name = "El pinche", type = PlaceType.RESTAURANT),
        Place(id = randomId(), name = "San Martín", type = PlaceType.RESTAURANT),
        Place(id = randomId(), name = "Jacarandas de Cayalá", type = PlaceType.PARK),
        Place(id = randomId(), name = "Parque la democracia", type = PlaceType.PARK),
        Place(id = randomId(), name = "Gallo negro", type = PlaceType.BAR),
        Place(id = randomId(), name = "Soho", type = PlaceType.BAR),
        Place(id = randomId(), name = "Parque Colón", type = PlaceType.PARK),
        Place(id = randomId(), name = "Pradera", type = PlaceType.SHOPPING_MALL),
        Place(id = randomId(), name = "Taco Bell", type = PlaceType.RESTAURANT),
        Place(id = randomId(), name = "Naranjo Mall", type = PlaceType.SHOPPING_MALL),
    )

    fun getPlaces() = places
}