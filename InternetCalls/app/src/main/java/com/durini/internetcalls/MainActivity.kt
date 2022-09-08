package com.durini.internetcalls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.durini.internetcalls.datasource.api.RetrofitInstance
import com.durini.internetcalls.datasource.model.AllAssetsResponse
import com.durini.internetcalls.datasource.model.AssetHistoryResponse
import com.durini.internetcalls.datasource.model.AssetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitInstance.api.getCrypto().enqueue(object: Callback<AllAssetsResponse> {
            override fun onResponse(
                call: Call<AllAssetsResponse>,
                response: Response<AllAssetsResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<AllAssetsResponse>, t: Throwable) {
                println("Error")
            }

        })

        RetrofitInstance.api.getCurrency("bitcoin").enqueue(object: Callback<AssetResponse> {
            override fun onResponse(call: Call<AssetResponse>, response: Response<AssetResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
                println("Error")
            }

        })

        RetrofitInstance.api.getCurrencyHistory("bitcoin", "d1").enqueue(object: Callback<AssetHistoryResponse> {
            override fun onResponse(
                call: Call<AssetHistoryResponse>,
                response: Response<AssetHistoryResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<AssetHistoryResponse>, t: Throwable) {
                print("Error")
            }

        })
    }
}