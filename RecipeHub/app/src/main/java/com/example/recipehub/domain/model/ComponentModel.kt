package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.ComponentDTO

data class ComponentModel(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientModel,
    val measurement: MeasurementModel,
    val position: Int
)

fun ComponentModel.toDTO(): ComponentDTO {
    return ComponentDTO(
        rawText = this.rawText,
        extraComment = this.extraComment,
        ingredient = this.ingredient.toDTO(),
        measurement = this.measurement.toDTO(),
        position = this.position
    )
}
