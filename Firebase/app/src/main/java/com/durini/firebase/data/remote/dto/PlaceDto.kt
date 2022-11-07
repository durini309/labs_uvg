package com.durini.firebase.data.remote.dto

import com.durini.firebase.data.local.entity.Place

data class PlaceDto(
    val internalID: Int = 0,
    val name: String = "",
    val isMaskMandatory: Boolean = false,
    var createdBy: String = "" // guarda el ID del usuario loggeado
)

fun PlaceDto.mapToEntity(): Place = Place(
    id = internalID,
    name = name,
    isMaskMandatory = isMaskMandatory
)