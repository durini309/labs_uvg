package com.durini.hilt.data.repository

interface MyRepository {
    suspend fun doNetworkCall()
}