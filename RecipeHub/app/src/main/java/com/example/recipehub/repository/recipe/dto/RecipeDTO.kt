package com.example.recipehub.repository.recipe.dto

data class RecipeDTO (
    val recipeID: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<ComponentDTO>,
    val instructions: List<InstructionDTO>,
    val ingredients: List<IngredientDTO>,
    val nutrition: NutritionDTO
)