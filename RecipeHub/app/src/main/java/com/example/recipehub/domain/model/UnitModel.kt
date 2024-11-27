package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.UnitDTO

data class UnitModel(
    val name: String,
    val displaySingular: String,
    val displayPlural: String,
    val abbreviation: String
)

fun UnitModel.toDTO(): UnitDTO {
    return UnitDTO(
        name = this.name,
        displaySingular = this.displaySingular,
        displayPlural = this.displayPlural,
        abbreviation = this.abbreviation
    )
}
