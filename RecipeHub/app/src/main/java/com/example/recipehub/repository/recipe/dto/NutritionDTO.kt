package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.repository.recipe.model.NutritionModel

data class NutritionDTO(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)

fun NutritionDTO.toModel(): NutritionModel {
    return NutritionModel(
        calories = this.calories,
        protein = this.protein,
        fat = this.fat,
        carbohydrates = this.carbohydrates,
        sugar = this.sugar,
        fiber = this.fiber
    )
}

fun List<NutritionDTO>.toModel(): List<NutritionModel> {
    return this.map { it.toModel() }
}