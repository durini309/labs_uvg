package com.durini.firebase.data.repository.place

import com.durini.firebase.data.local.entity.Place

interface PlaceRepository {
    suspend fun createPlace(place: Place, owner: String)
    suspend fun getPlace(id: String): Place?
    suspend fun getPlacesFilteredByMaskUsage(isMaskMandatory: Boolean): List<Place>?
}