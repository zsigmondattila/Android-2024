package com.example.recipehub.repository.recipe.mapper

import com.example.recipehub.domain.model.InstructionModel
import com.example.recipehub.repository.recipe.dto.InstructionDTO

fun InstructionDTO.toModel(): InstructionModel {
    return InstructionModel(
        instructionID = this.instructionID,
        displayText = this.displayText,
        position = this.position
    )
}

fun List<InstructionDTO>.toModel(): List<InstructionModel> {
    return this.map { it.toModel() }
}

fun InstructionModel.toDTO(): InstructionDTO {
    return InstructionDTO(
        instructionID = this.instructionID,
        displayText = this.displayText,
        position = this.position
    )
}

