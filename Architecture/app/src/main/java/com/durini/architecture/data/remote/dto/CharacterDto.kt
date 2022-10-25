package com.durini.architecture.data.remote.dto

import com.durini.architecture.data.local.entity.Character

data class CharacterDto(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: OriginDto,
    val episode: List<String>
)

fun CharacterDto.mapToEntity(): Character = Character(
    id = id.toInt(),
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image,
    origin = origin.name,
    episode = episode.size
)