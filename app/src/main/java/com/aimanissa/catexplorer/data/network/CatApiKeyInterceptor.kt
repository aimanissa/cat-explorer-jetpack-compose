package com.aimanissa.catexplorer.data.network

import okhttp3.Interceptor
import okhttp3.Response

internal class CatApiKeyInterceptor(private val apikey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .url(
                    chain.request().url.newBuilder()
                        .addQueryParameter("api_key", apikey)
                        .build()
                )
                .build()
        return chain.proceed(request)
    }
}