package com.aimanissa.catexplorer

import com.aimanissa.catexplorer.data.network.CatApi
import com.aimanissa.catexplorer.data.network.createCatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCatApi(okHttpClient: OkHttpClient): CatApi = createCatApi(
        baseUrl = BuildConfig.CAT_API_BASE_URL,
        apiKey = BuildConfig.CAT_API_KEY,
        okHttpClient = okHttpClient,
        json = Json { ignoreUnknownKeys = true }
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}