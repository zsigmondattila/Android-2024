package com.example.recipehub.repository.recipe.model

data class ComponentModel(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientModel,
    val measurement: MeasurementModel,
    val position: Int
)