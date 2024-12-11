package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.UnitModel
import com.example.recipehub.repository.recipe.dto.UnitDTO

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

fun UnitModel.toDTO(): UnitDTO {
    return UnitDTO(
        name = this.name ?: "",
        displaySingular = this.displaySingular ?: "",
        displayPlural = this.displayPlural ?: "",
        abbreviation = this.abbreviation ?: ""
    )
}
