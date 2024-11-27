package com.example.recipehub.domain.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<ComponentModel>,
    val instructions: List<InstructionModel>,
    val ingredients: List<IngredientModel>,
    val nutrition: NutritionModel
)

