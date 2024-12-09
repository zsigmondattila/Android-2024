package com.example.recipehub.ui.my_recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)

    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    suspend fun fetchRecipes() {
        val recipeList = recipeRepository.readRecipesFromDatabase()

        Log.d("RecipeListViewModel", "Fetched Recipes: $recipeList")

        _recipes.value = recipeList
    }
}
