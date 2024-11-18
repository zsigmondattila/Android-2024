package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.RecipeModel

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
//    val components: List<ComponentDTO>,
//    val instructions: List<InstructionDTO>,
//    val nutritions: List<NutritionDTO>
)

fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.recipeID,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        isPublic = this.isPublic ?: false,
//        components = this.components.map { it.toModel() },
//        instructions = this.instructions.map { it.toModel() },
//        nutrition = this.nutritions.map { it.toModel() },
        keywords = this.keywords,
        userEmail = this.userEmail,
        originalVideoUrl = this.originalVideoUrl,
        country = this.country,
        numServings = this.numServings
    )
}

fun List<RecipeDTO>.toModel(): List<RecipeModel> {
    return this.map { it.toModel() }
}