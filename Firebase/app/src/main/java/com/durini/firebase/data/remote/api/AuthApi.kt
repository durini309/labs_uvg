package com.durini.firebase.data.remote.api

import com.durini.firebase.data.Resource

interface AuthApi {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Resource<String>
}