package com.durini.internetcalls.datasource.api

import com.durini.internetcalls.datasource.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoAPI {

    @GET("/v2/assets")
    fun getCrypto(
        @Query("ids") ids: String? = null,
        @Query("limit") limit: Int? = 3
    ): Call<AllAssetsResponse>

    @GET("/v2/assets/{id}")
    fun getCurrency(
        @Path("id") id: String
    ): Call<AssetResponse>

    @GET("/v2/assets/{id}/history")
    fun getCurrencyHistory(
        @Path("id") id: String,
        @Query("interval") interval: String
    ): Call<AssetHistoryResponse>

}