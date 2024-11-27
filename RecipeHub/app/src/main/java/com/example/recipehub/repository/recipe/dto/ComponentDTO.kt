package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.ComponentModel
import com.example.recipehub.domain.model.IngredientModel
import com.example.recipehub.domain.model.MeasurementModel

data class ComponentDTO(
    val rawText: String,
    val extraComment: String,
    val ingredient: IngredientDTO,
    val measurement: MeasurementDTO,
    val position: Int
)