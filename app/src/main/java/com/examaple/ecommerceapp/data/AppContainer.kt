package com.examaple.ecommerceapp.data

import com.examaple.ecommerceapp.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


interface AppContainer{
    val networkRepository: NetworkRepository

}
class DefaultAppContainer: AppContainer {
    val baseUrl = "https://your-space-name.hf.space/"

    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    private val authInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        chain.proceed(requestBuilder.build())
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()
    val retrofitApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val networkRepository: NetworkRepository by lazy {
        NetworkRepository(retrofitApiService)
    }
}