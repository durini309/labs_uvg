package com.durini.solucionlab10.data.remote.api

import com.durini.solucionlab10.data.remote.dto.CharacterDto
import com.durini.solucionlab10.data.remote.dto.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("/api/character")
    fun getCharacters(): Call<CharactersResponse>

    @GET("/api/character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Call<CharacterDto>

}