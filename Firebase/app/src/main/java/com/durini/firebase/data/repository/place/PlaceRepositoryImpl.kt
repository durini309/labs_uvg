package com.durini.firebase.data.repository.place

import com.durini.firebase.data.local.entity.Place
import com.durini.firebase.data.local.entity.mapToDto
import com.durini.firebase.data.remote.api.PlaceApi
import com.durini.firebase.data.remote.dto.mapToEntity

class PlaceRepositoryImpl(
    private val api: PlaceApi
) : PlaceRepository {
    override suspend fun createPlace(place: Place, owner: String) {
        val dto = place.mapToDto().apply { createdBy = owner }
        api.insert(dto)
    }

    override suspend fun getPlace(id: String): Place? {
        val placeDto = api.getById(id)
        placeDto?.apply {
            return placeDto.mapToEntity()
        }
        return null
    }

    override suspend fun getPlacesFilteredByMaskUsage(isMaskMandatory: Boolean): List<Place>? {
        val filteredPlaces = api.getByMaskUsage(isMaskMandatory)
        filteredPlaces?.apply {
            return filteredPlaces.map { dto -> dto.mapToEntity() }
        }
        return null
    }
}