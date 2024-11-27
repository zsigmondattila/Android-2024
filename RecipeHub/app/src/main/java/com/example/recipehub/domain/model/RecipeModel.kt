package com.example.recipehub.domain.model
import com.example.recipehub.repository.recipe.dto.RecipeDTO


data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val keywords: String,
    val isPublic: Boolean,
    val userEmail: String,
    val originalVideoUrl: String,
    val country: String,
    val numServings: Int,
    val components: List<ComponentModel>,
    val instructions: List<InstructionModel>,
    val ingredients: List<IngredientModel>,
    val nutrition: NutritionModel
)

fun RecipeModel.toDTO(): RecipeDTO {
    return RecipeDTO(
        recipeID = this.id,
        name = this.name,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl,
        isPublic = this.isPublic,
        components = this.components.map { it.toDTO() },
        instructions = this.instructions.map { it.toDTO() },
        ingredients = this.ingredients.map { it.toDTO() },
        nutrition = this.nutrition.toDTO(),
        keywords = this.keywords,
        userEmail = this.userEmail,
        originalVideoUrl = this.originalVideoUrl,
        country = this.country,
        numServings = this.numServings
    )
}