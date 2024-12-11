package com.example.recipehub.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _bookmarkedRecipes = MutableLiveData<List<RecipeModel>>()
    val bookmarkedRecipes: LiveData<List<RecipeModel>> get() = _bookmarkedRecipes

    fun loadBookmarkedRecipes() {
        viewModelScope.launch {
            val recipes = recipeRepository.readRecipesFromDatabase()
            _bookmarkedRecipes.postValue(recipes)
        }
    }

    fun removeBookmark(recipe: RecipeModel) {
        viewModelScope.launch {
            recipeRepository.deleteRecipeFromDatabase(recipe)
            loadBookmarkedRecipes()
        }
    }
}
