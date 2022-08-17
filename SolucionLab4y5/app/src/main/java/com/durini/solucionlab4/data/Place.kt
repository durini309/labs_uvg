package com.durini.solucionlab4.data

import java.io.Serializable

data class Place(
    val name: String,
    val address: String,
    val schedule: String,
    val phone: String
) : Serializable
