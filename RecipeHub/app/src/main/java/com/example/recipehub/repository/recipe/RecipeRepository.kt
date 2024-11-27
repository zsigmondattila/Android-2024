package com.example.recipehub.repository.recipe

import android.content.Context
import android.util.Log
import com.example.recipehub.repository.recipe.dto.RecipeDTO
import com.example.recipehub.repository.recipe.mapper.toModel
import com.example.recipehub.domain.model.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import com.example.recipehub.data.entities.RecipeEntity
import org.json.JSONObject
import com.example.recipehub.api.RecipeApiService
import com.example.recipehub.data.database.RecipeDatabase
import com.example.recipehub.repository.recipe.mapper.toDTO

class RecipeRepository(private val context: Context) {

    private val database = RecipeDatabase.getDatabase(context)
    private val recipeDao = database.recipeDao()
    private val gson = Gson()

    suspend fun getAllRecipes(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            recipeDao.getAllRecipes().map {
                val jsonObject = JSONObject(it.json).apply {
                    put("id", it.internalId)
                }
                gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
            }
        }
    }

    suspend fun getAllRecipesFromAPI(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            val recipeList = mutableListOf<RecipeModel>()
            val response = RecipeApiService.getRecipes()
            for (recipeDTO in response) {
                recipeList.add(recipeDTO.toModel())
            }
            recipeList
        }
    }

    suspend fun saveRecipe(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val recipeJson = gson.toJson(recipe.toDTO())
            val recipeEntity = RecipeEntity(json = recipeJson)
            recipeDao.insertRecipe(recipeEntity)
        }
    }

    suspend fun deleteRecipe(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val recipeJson = gson.toJson(recipe.toDTO())
            val recipeEntity = RecipeEntity(json = recipeJson)
            recipeDao.deleteRecipe(recipeEntity)
        }
    }


    private fun readAllRecipes(context: Context): List<RecipeDTO> {
        val gson = Gson()
        var recipeList = listOf<RecipeDTO>()
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("all_recipes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val type = object : TypeToken<List<RecipeDTO>>() {}.type
            recipeList = gson.fromJson(jsonString, type)

            Log.i("GSON", recipeList.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return recipeList
    }

}
