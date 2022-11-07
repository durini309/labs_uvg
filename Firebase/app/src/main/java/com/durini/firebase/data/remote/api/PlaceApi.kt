package com.durini.firebase.data.remote.api

import com.durini.firebase.data.remote.dto.PlaceDto

interface PlaceApi {
    suspend fun insert(place: PlaceDto)
    suspend fun getById(id: String): PlaceDto?
    suspend fun getByMaskUsage(isMaskMandatory: Boolean): List<PlaceDto>?
}