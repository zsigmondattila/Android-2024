package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.MeasurementModel

data class MeasurementDTO(
    val quantity: String,
    val unit: UnitDTO
)

fun MeasurementDTO.toModel(): MeasurementModel {
    return MeasurementModel(
        quantity = this.quantity,
        unit = this.unit.toModel()
    )
}

fun List<MeasurementDTO>.toModel(): List<MeasurementModel> {
    return this.map { it.toModel() }
}