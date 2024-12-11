package com.example.recipehub.ui.my_recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import com.example.recipehub.utils.SharedPreferences

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)
    private val sharedPreferences = SharedPreferences(application)

    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    suspend fun fetchRecipes() {
        val recipeList = recipeRepository.readRecipesFromAPI()

        val userEmail = sharedPreferences.getUserEmail()

        Log.d("MyRecipesViewModel", "Fetched Recipes: $recipeList")

        val myRecipes = recipeList.filter { recipe ->
            recipe.userEmail == userEmail
        }

        _recipes.value = myRecipes
    }
}

