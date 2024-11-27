package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.NutritionModel
import com.example.recipehub.repository.recipe.dto.NutritionDTO

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
