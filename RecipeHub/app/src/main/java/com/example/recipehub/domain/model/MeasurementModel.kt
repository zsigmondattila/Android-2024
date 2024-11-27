package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.MeasurementDTO

data class MeasurementModel(
    val quantity: String,
    val unit: UnitModel
)

fun MeasurementModel.toDTO(): MeasurementDTO {
    return MeasurementDTO(
        quantity = this.quantity,
        unit = this.unit.toDTO()
    )
}
