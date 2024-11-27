package com.example.recipehub.domain.model

import com.example.recipehub.repository.recipe.dto.InstructionDTO

data class InstructionModel(
    val instructionID: Int,
    val displayText: String,
    val position: Int
)

fun InstructionModel.toDTO(): InstructionDTO {
    return InstructionDTO(
        instructionID = this.instructionID,
        displayText = this.displayText,
        position = this.position
    )
}

