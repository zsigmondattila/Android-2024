package com.example.recipehub.ui.recipe_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository

class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)

    private val _recipe = MutableLiveData<RecipeModel?>()
    val recipe: LiveData<RecipeModel?> get() = _recipe

    suspend fun getRecipeById(recipeId: Int): LiveData<RecipeModel?> {
        val allRecipes = recipeRepository.getAllRecipes()
        _recipe.value = allRecipes.find { it.id == recipeId }
        return recipe
    }
}
