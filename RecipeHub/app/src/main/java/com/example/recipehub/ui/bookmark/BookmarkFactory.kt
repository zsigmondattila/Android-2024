package com.example.recipehub.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipehub.repository.recipe.RecipeRepository

class BookmarkFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {

    // A 'create' metódusnak pontosan illeszkednie kell a ViewModelProvider.Factory interfészhez
    @Suppress("UNCHECKED_CAST")  // Figyelmeztetés kikapcsolása, hogy ne okozzon problémát
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
