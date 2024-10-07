package com.example.capxtask.server

import com.example.capxtask.models.CryptoCurrency
import com.example.capxtask.models.MarketModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApiService {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=100")
    suspend fun getCryptos(): Response<MarketModel>
}
