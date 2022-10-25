package com.durini.architecture.data.remote

import com.durini.architecture.data.remote.dto.ResponseDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): ResponseDto

}