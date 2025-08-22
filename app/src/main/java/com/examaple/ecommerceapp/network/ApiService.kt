package com.examaple.ecommerceapp.network

import com.examaple.ecommerceapp.model.AuthResponse
import com.examaple.ecommerceapp.model.LoginRequest
import com.examaple.ecommerceapp.model.ProductResponse
import com.examaple.ecommerceapp.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products/")
    suspend fun getProducts(): List<ProductResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse
}
