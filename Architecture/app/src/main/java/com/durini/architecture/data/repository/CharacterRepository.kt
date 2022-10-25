package com.durini.architecture.data.repository

import com.durini.architecture.data.Resource
import com.durini.architecture.data.local.entity.Character

interface CharacterRepository {

    suspend fun getCharacters(): Resource<List<Character>>

}