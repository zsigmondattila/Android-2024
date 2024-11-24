package com.example.recipehub.repository.recipe

import android.content.Context
import android.util.Log
import com.example.recipehub.repository.recipe.dto.RecipeDTO
import com.example.recipehub.repository.recipe.dto.toModel
import com.example.recipehub.domain.model.RecipeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileOutputStream
import java.io.IOException

class RecipeRepository(private val context: Context) {

    private val gson = Gson()

    fun getAllRecipes(): List<RecipeModel> {
        return readAllRecipes(context).toModel()
    }

    suspend fun saveRecipe(recipe: RecipeModel) {
        val recipeList = readAllRecipes(context).toModel().toMutableList()
        recipeList.add(recipe)

        val jsonString = gson.toJson(recipeList)

        try {
            val outputStream: FileOutputStream = context.openFileOutput("all_recipes.json", Context.MODE_PRIVATE)
            outputStream.write(jsonString.toByteArray())
            outputStream.close()

            Log.i("GSON", "Recipe saved successfully")
        } catch (e: IOException) {
            e.printStackTrace()
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
