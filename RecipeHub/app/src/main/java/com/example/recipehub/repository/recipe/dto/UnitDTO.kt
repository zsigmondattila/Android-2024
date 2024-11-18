package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.UnitModel

data class UnitDTO(
    val name: String,
    val displaySingular: String,
    val displayPlural: String,
    val abbreviation: String
)

fun UnitDTO.toModel(): UnitModel {
    return UnitModel(
        name = this.name,
        displaySingular = this.displaySingular,
        displayPlural = this.displayPlural,
        abbreviation = this.abbreviation
    )
}

fun List<UnitDTO>.toModel(): List<UnitModel> {
    return this.map { it.toModel() }
}