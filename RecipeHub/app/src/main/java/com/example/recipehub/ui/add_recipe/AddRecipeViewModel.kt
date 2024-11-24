package com.example.recipehub.ui.add_recipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch

class AddRecipeViewModel(private val context: Context) : ViewModel() {

    private val recipeRepository = RecipeRepository(context)

    fun saveRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            recipeRepository.saveRecipe(recipe)
        }
    }
}
