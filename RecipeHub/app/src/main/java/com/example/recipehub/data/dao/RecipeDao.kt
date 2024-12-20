package com.example.recipehub.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipehub.data.entities.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?

    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE isBookmarked = 1")
    suspend fun getBookmarkedRecipes(): List<RecipeEntity>

    @Query("UPDATE recipe SET isBookmarked = :isBookmarked WHERE internalId = :id")
    suspend fun updateBookmarkStatus(id: Int, isBookmarked: Boolean)
}
