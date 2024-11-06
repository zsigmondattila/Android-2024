package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.repository.recipe.model.ComponentModel
import com.example.recipehub.repository.recipe.model.IngredientModel
import com.example.recipehub.repository.recipe.model.MeasurementModel

data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientDTO,
    val measurement: MeasurementDTO,
    val position: Int
)

fun ComponentDTO.toModel(): ComponentModel {
    return ComponentModel(
        rawText = this.rawText,
        ingredient = this.ingredient.toModel(),
        measurement = this.measurement.toModel(),
        extraComment = this.extraComment,
        position = this.position
    )
}

fun List<ComponentDTO>.toModel(): List<ComponentModel> {
    return this.map { it.toModel() }
}