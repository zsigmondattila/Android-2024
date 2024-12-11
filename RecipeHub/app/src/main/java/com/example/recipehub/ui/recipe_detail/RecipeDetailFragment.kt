package com.example.recipehub.ui.recipe_detail

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.LiveData
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRecipeDetailBinding.bind(view)

        val recipeId = arguments?.getInt("RECIPE_ID") ?: 0

        viewModel = ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        viewModel.recipe.observe(viewLifecycleOwner, Observer { recipe ->
            if (recipe != null) {
                displayRecipeDetails(recipe)
                binding.addToBookmarkButton.setOnClickListener {
                    viewModel.addToBookmark(recipe)
                }
            }
        })

        viewModel.getRecipeById(recipeId)
    }

    private fun displayRecipeDetails(recipe: RecipeModel) {
        binding.recipeTitle.text = recipe.name
        binding.recipeDescription.text = recipe.description
        binding.recipeKeywords.text = recipe.keywords

        Glide.with(binding.recipeImage.context)
            .load(recipe.thumbnailUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(binding.recipeImage)

        val componentsLayout = binding.recipeComponents
        componentsLayout.removeAllViews()
        recipe.components.forEach { component ->
            val ingredientText = "${component.ingredient.name}"
            val quantityText = "${component.measurement.quantity} ${component.measurement.unit.abbreviation}"

            val componentLayout = LinearLayout(requireContext())
            componentLayout.orientation = LinearLayout.HORIZONTAL
            componentLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val ingredientNameTextView = TextView(requireContext()).apply {
                text = ingredientText
                textSize = 17f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val ingredientDetailsTextView = TextView(requireContext()).apply {
                text = quantityText
                textSize = 14f
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                gravity = Gravity.END
            }

            componentLayout.addView(ingredientNameTextView)
            componentLayout.addView(ingredientDetailsTextView)

            val divider = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
                )
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColorSecondary))
            }

            componentsLayout.addView(componentLayout)
            componentsLayout.addView(divider)
        }

        val instructionsLayout = binding.recipeInstructions
        instructionsLayout.removeAllViews()
        recipe.instructions.forEachIndexed { index, instruction ->
            val instructionText = "${index + 1}. ${instruction.displayText}"
            val instructionTextView = TextView(requireContext()).apply {
                text = instructionText
                textSize = 14f
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
                setPadding(0, 8, 0, 8)
            }
            instructionsLayout.addView(instructionTextView)
        }

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
