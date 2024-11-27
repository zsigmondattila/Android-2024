package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.ComponentModel
import com.example.recipehub.domain.model.toDTO
import com.example.recipehub.repository.recipe.dto.ComponentDTO

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

fun ComponentModel.toDTO(): ComponentDTO {
    return ComponentDTO(
        rawText = this.rawText,
        extraComment = this.extraComment,
        ingredient = this.ingredient.toDTO(),
        measurement = this.measurement.toDTO(),
        position = this.position
    )
}