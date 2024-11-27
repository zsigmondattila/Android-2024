package com.example.recipehub.api

import com.example.recipehub.repository.recipe.dto.RecipeDTO
import retrofit2.http.GET

interface RecipeApiService {
    @GET("api/recipes")
    suspend fun getRecipes(): List<RecipeDTO>

    @GET("api/recipes/{id}")
    suspend fun getRecipeById(id: Int): RecipeDTO

}