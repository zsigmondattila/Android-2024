package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.MeasurementModel
import com.example.recipehub.domain.model.toDTO
import com.example.recipehub.repository.recipe.dto.MeasurementDTO

fun MeasurementDTO.toModel(): MeasurementModel {
    return MeasurementModel(
        quantity = this.quantity,
        unit = this.unit.toModel()
    )
}

fun List<MeasurementDTO>.toModel(): List<MeasurementModel> {
    return this.map { it.toModel() }
}

fun MeasurementModel.toDTO(): MeasurementDTO {
    return MeasurementDTO(
        quantity = this.quantity,
        unit = this.unit.toDTO()
    )
}
