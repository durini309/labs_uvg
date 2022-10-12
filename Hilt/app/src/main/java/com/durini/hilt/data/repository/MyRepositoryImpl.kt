package com.durini.hilt.data.repository

import android.content.Context
import com.durini.hilt.R
import com.durini.hilt.data.remote.MyApi

class MyRepositoryImpl(
    private val api: MyApi,
    private val context: Context
): MyRepository {

    init {
        val appName = context.getString(R.string.app_name)
        println("Hello from $appName")
    }

    override suspend fun doNetworkCall() {

    }
}