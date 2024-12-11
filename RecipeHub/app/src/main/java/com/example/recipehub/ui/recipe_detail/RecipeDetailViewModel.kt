package com.example.recipehub.ui.recipe_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)

    private val _recipe = MutableLiveData<RecipeModel?>()
    val recipe: LiveData<RecipeModel?> get() = _recipe

    fun getRecipeById(recipeId: Int) {
        viewModelScope.launch {
            val recipeData = recipeRepository.getRecipeByIdFromAPI(recipeId)
            _recipe.value = recipeData
        }
    }

    fun addToBookmark(recipe: RecipeModel) {
        viewModelScope.launch {
            recipeRepository.saveRecipeToDatabase(recipe)
            Log.d("RecipeDetailViewModel", "Added to bookmark: $recipe")
        }
    }
}
