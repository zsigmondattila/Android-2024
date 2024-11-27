package com.example.recipehub.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeApiClient {
    private const val BASE_URL =
        "https://recipe-appservice-cthjbdfafnhfdtes.germanywestcentral-01.azurewebsites.net/"

    // Singleton Retrofit példány, amelyet a RecipeApiService hívására használhatunk
    val apiService: RecipeApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RecipeApiService::class.java)
    }
}
