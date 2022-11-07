package com.durini.firebase.data.repository.auth

import com.durini.firebase.data.Resource
import com.durini.firebase.data.remote.api.AuthApi

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun signInWithEmailAndPassword(email: String, password: String) : String? {
        val authResponse = authApi.signInWithEmailAndPassword(email, password)

        return if (authResponse is Resource.Success)
            authResponse.data!!
        else
            null
    }
}