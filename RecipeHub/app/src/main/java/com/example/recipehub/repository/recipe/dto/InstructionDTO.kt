package com.example.recipehub.repository.recipe.dto

import com.example.recipehub.domain.model.InstructionModel

data class InstructionDTO(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)

fun InstructionDTO.toModel(): InstructionModel {
    return InstructionModel(
        displayText = this.displayText,
        position = this.position
    )
}

fun List<InstructionDTO>.toModel(): List<InstructionModel> {
    return this.map { it.toModel() }
}

