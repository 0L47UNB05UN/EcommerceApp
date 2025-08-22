package com.examaple.ecommerceapp.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int,
    val name: String,
    val description: String? = null,
    val price: Double,
    val imageUrl: String? = null,
    val ownerId: Int
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val accessToken: String,
    val tokenType: String
)

