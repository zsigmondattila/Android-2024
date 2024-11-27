package com.example.recipehub.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiClient {
    companion object {
        private const val BASE_URL =
            "https://recipe-appservice-cthjbdfafnhfdtes.germanywestcentral-01.azurewebsites.net/"
    }

    private val recipeApiService: RecipeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeApiService = retrofit.create(RecipeApiService::class.java)
    }

    suspend fun getRecipes() = recipeApiService.getRecipes()

}