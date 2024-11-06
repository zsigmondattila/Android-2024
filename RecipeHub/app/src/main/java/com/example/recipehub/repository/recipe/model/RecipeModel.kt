package com.example.recipehub.repository.recipe.model

data class RecipeModel(
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
//    val components: List<ComponentModel>,
//    val instructions: List<InstructionModel>,
//    val nutrition: List<NutritionModel>
)