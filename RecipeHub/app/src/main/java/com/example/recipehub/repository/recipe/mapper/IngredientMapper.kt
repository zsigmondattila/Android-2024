package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.IngredientModel
import com.example.recipehub.repository.recipe.dto.IngredientDTO

fun IngredientDTO.toModel(): IngredientModel {
    return IngredientModel(name = this.name)
}

fun List<IngredientDTO>.toModel(): List<IngredientModel> {
    return this.map { it.toModel() }
}

fun IngredientModel.toDTO(): IngredientDTO {
    return IngredientDTO(
        name = this.name
    )
}
