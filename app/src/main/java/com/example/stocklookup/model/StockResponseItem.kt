package com.example.stocklookup.model


import com.google.gson.annotations.SerializedName

data class StockResponseItem(
    @SerializedName("avgVolume")
    val avgVolume: Double,
    @SerializedName("change")
    val change: Double,
    @SerializedName("changesPercentage")
    val changesPercentage: Double,
    @SerializedName("dayHigh")
    val dayHigh: Double,
    @SerializedName("dayLow")
    val dayLow: Double,
    @SerializedName("earningsAnnouncement")
    val earningsAnnouncement: String,
    @SerializedName("eps")
    val eps: Double,
    @SerializedName("exchange")
    val exchange: String,
    @SerializedName("marketCap")
    val marketCap: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("open")
    val `open`: Double,
    @SerializedName("pe")
    val pe: Double,
    @SerializedName("previousClose")
    val previousClose: Double,
    @SerializedName("price")
    val price: Double,
    @SerializedName("priceAvg200")
    val priceAvg200: Double,
    @SerializedName("priceAvg50")
    val priceAvg50: Double,
    @SerializedName("sharesOutstanding")
    val sharesOutstanding: Long,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("volume")
    val volume: Double,
    @SerializedName("yearHigh")
    val yearHigh: Double,
    @SerializedName("yearLow")
    val yearLow: Double
)