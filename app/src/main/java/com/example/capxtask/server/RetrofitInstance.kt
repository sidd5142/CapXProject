package com.example.capxtask.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: CryptoApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.coinmarketcap.com/")  // Replace with actual API URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApiService::class.java)
    }
}