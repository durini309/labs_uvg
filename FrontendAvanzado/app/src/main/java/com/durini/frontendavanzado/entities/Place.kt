package com.durini.frontendavanzado.entities

import java.io.Serializable

data class Place(
    val id: String,
    var name: String,
    var type: PlaceType
): Serializable

enum class PlaceType {
    RESTAURANT,
    BAR,
    SHOPPING_MALL,
    PARK
}