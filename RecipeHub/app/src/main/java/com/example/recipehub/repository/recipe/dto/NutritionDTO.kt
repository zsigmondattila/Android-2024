package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.NutritionModel

data class NutritionDTO(
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbohydrates: Int,
    val sugar: Int,
    val fiber: Int
)

fun NutritionDTO?.toModel(): NutritionModel {
    return this?.let {
        NutritionModel(
            calories = it.calories,
            protein = it.protein,
            fat = it.fat,
            carbohydrates = it.carbohydrates,
            sugar = it.sugar,
            fiber = it.fiber
        )
    } ?: NutritionModel(0, 0, 0, 0, 0, 0)
}


fun List<NutritionDTO>.toModel(): List<NutritionModel> {
    return this.map { it.toModel() }
}