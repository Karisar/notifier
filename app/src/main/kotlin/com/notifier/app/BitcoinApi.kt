package com.notifier.app

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class CryptoPriceResponse(
    @SerializedName("bitcoin") val bitcoin: CryptoPrice?,
    @SerializedName("ripple") val ripple: CryptoPrice?,
    @SerializedName("solana") val solana: CryptoPrice?
)

data class CryptoPrice(
    @SerializedName("eur") val eur: Double
)

interface CryptoApi {
    @GET("api/v3/simple/price")
    suspend fun getPrices(
        @Query("ids") ids: String = "bitcoin,ripple,solana",
        @Query("vs_currencies") vsCurrencies: String = "eur"
    ): CryptoPriceResponse
}

object CryptoService {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoApi::class.java)

    suspend fun getPricesInEur(): CryptoPriceResponse = api.getPrices()
}
