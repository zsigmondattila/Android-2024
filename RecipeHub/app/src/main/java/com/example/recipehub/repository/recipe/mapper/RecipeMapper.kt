package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.dto.RecipeDTO

fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.recipeID,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        isPublic = this.isPublic ?: false,
        components = this.components.map { it.toModel() },
        instructions = this.instructions.map { it.toModel() },
        ingredients = this.ingredients.map { it.toModel() },
        nutrition = this.nutrition.toModel(),
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

fun RecipeModel.toDTO(): RecipeDTO {
    return RecipeDTO(
        recipeID = this.id,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        isPublic = this.isPublic,
        components = this.components.map { it.toDTO() },
        instructions = this.instructions.map { it.toDTO() },
        ingredients = this.ingredients.map { it.toDTO() },
        nutrition = this.nutrition.toDTO(),
        keywords = this.keywords,
        userEmail = this.userEmail,
        originalVideoUrl = this.originalVideoUrl,
        country = this.country,
        numServings = this.numServings
    )
}