package com.durini.solucionlab10.datasource.model

data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: Origin,
    val episode: List<String>
)
