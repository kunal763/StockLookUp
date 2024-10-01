package com.example.stocklookup.api

import android.provider.Settings.Global.getString
import com.example.stocklookup.R
import com.example.stocklookup.model.StockResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockApi {

    @GET("quote/{symbol}")
    suspend fun getStockData(
        @Path("symbol") symbol: String,
        @Query("apikey") apiKey: String,
    ): StockResponse
}
object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://financialmodelingprep.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: StockApi by lazy { retrofit.create(StockApi::class.java) }
}