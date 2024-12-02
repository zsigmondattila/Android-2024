package com.example.recipehub.repository.recipe

import android.content.Context
import android.util.Log
import com.example.recipehub.api.RecipeApiClient
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
import com.example.recipehub.data.database.RecipeDatabase
import com.example.recipehub.repository.recipe.mapper.toDTO

class RecipeRepository(private val context: Context) {

    private val database = RecipeDatabase.getDatabase(context)
    private val recipeDao = database.recipeDao()
    private val gson = Gson()

    suspend fun readRecipesFromJSON(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            val jsonString = loadJsonFromAssets("all_recipes.json")
            val recipeList: List<RecipeDTO> = gson.fromJson(jsonString, object : TypeToken<List<RecipeDTO>>() {}.type)
            recipeList.map { it.toModel() }
        }
    }

    suspend fun saveRecipeToJSON(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val jsonString = gson.toJson(recipe.toDTO())
            saveJsonToFile(jsonString, "recipe.json")
        }
    }

    suspend fun readRecipesFromDatabase(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            recipeDao.getAllRecipes().map {
                val jsonObject = JSONObject(it.json).apply {
                    put("id", it.internalId)
                }
                gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
            }
        }
    }

    suspend fun saveRecipeToDatabase(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val recipeJson = gson.toJson(recipe.toDTO())
            val recipeEntity = RecipeEntity(json = recipeJson)
            recipeDao.insertRecipe(recipeEntity)
        }
    }

    suspend fun readRecipesFromAPI(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            val recipeList = mutableListOf<RecipeModel>()
            try {
                val response = RecipeApiClient.apiService.getRecipes()

                Log.d("RecipeRepository", "API response: $response")

                if (response != null && response.isNotEmpty()) {
                    for (recipeDTO in response) {
                        recipeDTO?.let {
                            recipeList.add(it.toModel())
                        }
                    }
                } else {
                    Log.e("RecipeRepository", "API response is empty or null")
                }
            } catch (e: Exception) {
                Log.e("RecipeRepository", "Failed to fetch recipes from API: ${e.message}")
            }
            return@withContext recipeList
        }
    }

    suspend fun getRecipeByIdFromAPI(recipeId: Int): RecipeModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RecipeApiClient.apiService.getRecipeById(recipeId)

                return@withContext response.toModel()
            } catch (e: Exception) {
                Log.e("RecipeRepository", "Failed to fetch recipe by ID: ${e.message}")
                Log.e("RecipeRepository", "Recipe ID: $recipeId")
                return@withContext null
            }
        }
    }

    suspend fun deleteRecipeFromDatabase(recipe: RecipeModel) {
        withContext(Dispatchers.IO) {
            val recipeJson = gson.toJson(recipe.toDTO())
            val recipeEntity = RecipeEntity(json = recipeJson)
            recipeDao.deleteRecipe(recipeEntity)
        }
    }

    private fun loadJsonFromAssets(fileName: String): String {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }

    private fun saveJsonToFile(jsonString: String, fileName: String) {

    }

}
