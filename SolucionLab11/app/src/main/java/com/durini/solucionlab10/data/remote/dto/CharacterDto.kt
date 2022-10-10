package com.durini.solucionlab10.data.remote.dto

import com.durini.solucionlab10.data.local.model.Character

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

fun CharacterDto.mapToModel(): Character = Character(
    id = id.toInt(),
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image,
    origin = origin.name,
    episodes = episode.size
)