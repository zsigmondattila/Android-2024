package com.example.recipehub.domain.model

data class UserModel(
    val id: String,
    val email: String,
    val givenName: String?,
    val familyName: String?,
    val picture: String?
)
