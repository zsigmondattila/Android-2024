package com.example.recipehub.ui.recipe_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.recipehub.R
import com.example.recipehub.databinding.FragmentRecipeDetailBinding
import com.example.recipehub.domain.model.RecipeModel
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecipeDetailViewModel


    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the recipe ID (assuming it's passed as an argument or obtained somehow)
        val recipeId = arguments?.getInt("RECIPE_ID") ?: 0

        viewModel = ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        // Use viewModelScope.launch to call suspend function inside a coroutine
        viewModel.viewModelScope.launch {
            viewModel.getRecipeById(recipeId).observe(viewLifecycleOwner, Observer { recipe ->
                if (recipe != null) {
                    displayRecipeDetails(recipe)
                }
            })
        }
    }
    private fun displayRecipeDetails(recipe: RecipeModel) {
        binding.recipeTitle.text = recipe.name
        binding.recipeDescription.text = recipe.description
        binding.recipeKeywords.text = recipe.keywords

        Glide.with(binding.recipeImage.context)
            .load(recipe.thumbnailUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(binding.recipeImage)

        val ingredientsText = recipe.components.joinToString("\n") { "${it.measurement.quantity} ${it.measurement.unit.abbreviation} ${it.ingredient.name}" }
        binding.recipeComponents.text = ingredientsText

        val instructionsText = recipe.instructions.joinToString("\n") { it.displayText }
        binding.recipeInstructions.text = instructionsText

        binding.recipeNutrition.text = getString(
            R.string.nutrition_facts,
            recipe.nutrition.calories,
            recipe.nutrition.protein,
            recipe.nutrition.fat,
            recipe.nutrition.carbohydrates,
            recipe.nutrition.sugar,
            recipe.nutrition.fiber
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
