package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.ComponentDTO

data class ComponentModel(
    val rawText: String,
    val extraComment: String?,
    val ingredient: IngredientModel,
    val measurement: MeasurementModel,
    val position: Int
)


