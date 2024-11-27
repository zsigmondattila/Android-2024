package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.IngredientDTO

data class IngredientModel(
    val name: String
)

fun IngredientModel.toDTO(): IngredientDTO {
    return IngredientDTO(
        name = this.name
    )
}
