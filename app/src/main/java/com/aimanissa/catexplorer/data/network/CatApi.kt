package com.aimanissa.catexplorer.data.network

import com.aimanissa.catexplorer.data.network.model.CatImageDTO
import com.aimanissa.catexplorer.data.network.utils.CatApiKeyInterceptor
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API details [here](https://developers.thecatapi.com/)
 */
interface CatApi {

    @GET("search")
    suspend fun getRandomImage(
        @Query("breed_ids") breeds: String = "beng"
    ): Result<List<CatImageDTO>>
}

fun createCatApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): CatApi {
    return retrofit(baseUrl, apiKey, okHttpClient, json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val modifiedOkHttpClient =
        (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
            .addInterceptor(CatApiKeyInterceptor(apiKey))
            .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}