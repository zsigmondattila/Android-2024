package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.NutritionDTO

data class NutritionModel(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)

fun NutritionModel.toDTO(): NutritionDTO {
    return NutritionDTO(
        calories = this.calories,
        protein = this.protein,
        fat = this.fat,
        carbohydrates = this.carbohydrates,
        sugar = this.sugar,
        fiber = this.fiber
    )
}
