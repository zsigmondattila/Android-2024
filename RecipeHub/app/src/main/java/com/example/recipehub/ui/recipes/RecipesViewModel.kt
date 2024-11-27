package com.example.recipehub.ui.recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipehub.repository.recipe.RecipeRepository
import com.example.recipehub.domain.model.RecipeModel

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)

    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    suspend fun fetchRecipes() {
        val recipeList = recipeRepository.getAllRecipes()

        Log.d("RecipeListViewModel", "Fetched Recipes: $recipeList")

        _recipes.value = recipeList
    }

}