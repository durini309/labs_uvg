package com.durini.solucionlab8y9.datasource.api

import com.durini.solucionlab8y9.datasource.model.Character
import com.durini.solucionlab8y9.datasource.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("/api/character")
    fun getCharacters(): Call<CharactersResponse>

    @GET("/api/character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Call<Character>

}