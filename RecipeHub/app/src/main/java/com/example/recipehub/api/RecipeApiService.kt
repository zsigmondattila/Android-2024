package com.example.recipehub.api

import com.example.recipehub.repository.recipe.dto.RecipeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {
    @GET("api/recipes")
    suspend fun getRecipes(): List<RecipeDTO>

    @GET("api/recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): RecipeDTO
}
