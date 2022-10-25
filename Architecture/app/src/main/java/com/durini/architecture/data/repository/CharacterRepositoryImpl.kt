package com.durini.architecture.data.repository

import com.durini.architecture.data.Resource
import com.durini.architecture.data.local.dao.CharacterDao
import com.durini.architecture.data.local.entity.Character
import com.durini.architecture.data.remote.RickAndMortyApi
import com.durini.architecture.data.remote.dto.mapToEntity

class CharacterRepositoryImpl(
    private val characterDao: CharacterDao,
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(): Resource<List<Character>> {
        val localCharacters = characterDao.getCharacters()
        return try {
            if (localCharacters.isEmpty()) {
                val remoteCharacters =
                    api.getCharacters().results // List<CharacterDto> -> List<Character>
                val mappedCharacters =
                    remoteCharacters.map { characterDto -> characterDto.mapToEntity() }
                characterDao.insertAll(mappedCharacters)
                Resource.Success(mappedCharacters)
            } else {
                Resource.Success(localCharacters)
            }
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "")
        }
    }
}