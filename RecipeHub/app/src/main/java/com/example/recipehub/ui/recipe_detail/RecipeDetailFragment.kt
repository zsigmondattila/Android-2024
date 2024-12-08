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
            }
        })

        viewModel.getRecipeById(recipeId)
    }

    private fun displayRecipeDetails(recipe: RecipeModel) {
        // Ensure that binding is not null before accessing it
        if (_binding != null) {
            binding.recipeTitle.text = recipe.name
            binding.recipeDescription.text = recipe.description
            binding.recipeKeywords.text = recipe.keywords

            Glide.with(binding.recipeImage.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.recipeImage)

            // A hozzávalók formázása
            val componentsLayout = binding.recipeComponents

// Töröljük az előző tartalmat
            componentsLayout.removeAllViews()

// Hozzáadjuk az összetevőket egyenként
            recipe.components.forEach { component ->
                val ingredientText = "${component.ingredient.name}"
                val quantityText = "${component.measurement.quantity} ${component.measurement.unit.abbreviation}"

                // Létrehozunk egy új LinearLayout-ot, amely tartalmazza az összetevőket
                val componentLayout = LinearLayout(requireContext())
                componentLayout.orientation = LinearLayout.HORIZONTAL
                componentLayout.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                // Bal oldali TextView (összetevő neve)
                val ingredientNameTextView = TextView(requireContext()).apply {
                    text = ingredientText
                    textSize = 17f
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                }

                // Jobb oldali TextView (mennyiség + egység)
                val ingredientDetailsTextView = TextView(requireContext()).apply {
                    text = quantityText
                    textSize = 14f
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.END // A szöveg jobbra igazítása
                }

                // Hozzáadjuk a TextView-kat a LinearLayout-hoz
                componentLayout.addView(ingredientNameTextView)
                componentLayout.addView(ingredientDetailsTextView)

                val divider = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1 // A vonal magassága
                    )
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.textColorSecondary)) // A vonal színe
                }

                // Hozzáadjuk a componentLayout-ot a fő layout-hoz
                componentsLayout.addView(componentLayout)
                componentsLayout.addView(divider)
            }


            // Az elkészítési utasítások formázása
            val instructionsLayout = binding.recipeInstructions

// Töröljük az előző tartalmat
            instructionsLayout.removeAllViews()

// Hozzáadjuk az utasításokat egyenként
            recipe.instructions.forEachIndexed { index, instruction ->
                val instructionText = "${index + 1}. ${instruction.displayText}" // Sorszám hozzáadása

                // Létrehozunk egy új TextView-t minden egyes utasításhoz
                val instructionTextView = TextView(requireContext()).apply {
                    text = instructionText
                    textSize = 14f
                    setTextColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
                    // Padding beállítása minden TextView-nál
                    setPadding(0, 8, 0, 8)  // Felső és alsó padding
                }

                // Hozzáadjuk az új TextView-t a layout-hoz
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
