package com.examaple.ecommerceapp.data

import com.examaple.ecommerceapp.model.AuthResponse
import com.examaple.ecommerceapp.model.LoginRequest
import com.examaple.ecommerceapp.model.ProductResponse
import com.examaple.ecommerceapp.model.RegisterRequest
import com.examaple.ecommerceapp.network.ApiService


interface AppRepository{
    suspend fun getProducts(): List<ProductResponse>

    suspend fun login(request: LoginRequest): AuthResponse

    suspend fun register(request: RegisterRequest): AuthResponse
}

class NetworkRepository(
    val retrofitService: ApiService
): AppRepository{
    override suspend fun getProducts(): List<ProductResponse> {
        return retrofitService.getProducts()
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return retrofitService.login(request)
    }

    override suspend fun register(request: RegisterRequest): AuthResponse {
        return retrofitService.register(request)
    }
}