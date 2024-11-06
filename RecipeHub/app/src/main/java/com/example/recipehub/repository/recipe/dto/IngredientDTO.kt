package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.repository.recipe.model.IngredientModel

data class IngredientDTO(
    val name: String
)

fun IngredientDTO.toModel(): IngredientModel {
    return IngredientModel(name = this.name)
}

fun List<IngredientDTO>.toModel(): List<IngredientModel> {
    return this.map { it.toModel() }
}