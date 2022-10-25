package com.durini.architecture.data.repository

import com.durini.architecture.data.Resource
import com.durini.architecture.data.local.entity.Character

class FakeCharacterRepositoryImpl : CharacterRepository {
    override suspend fun getCharacters(): Resource<List<Character>> {
        val characters = listOf(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = "alive",
                species = "human",
                gender = "male",
                image = "",
                origin = "earth",
                episode = 55
            ),
            Character(
                id = 2,
                name = "Morty Smith",
                status = "alive",
                species = "human",
                gender = "male",
                image = "",
                origin = "earth",
                episode = 54
            )
        )

        return Resource.Success(characters)
    }
}