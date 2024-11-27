package com.example.recipehub.ui.add_recipe

import AddRecipeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.recipehub.R
import com.example.recipehub.domain.model.IngredientModel
import com.example.recipehub.domain.model.InstructionModel
import com.example.recipehub.domain.model.NutritionModel
import com.example.recipehub.domain.model.RecipeModel

class AddRecipeFragment : Fragment() {

    val addRecipeViewModel = ViewModelProvider(this).get(AddRecipeViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)

        val ingredientsContainer: LinearLayout = view.findViewById(R.id.ingredientsContainer)
        val instructionsContainer: LinearLayout = view.findViewById(R.id.instructionsContainer)

        view.findViewById<View>(R.id.addIngredientButton).setOnClickListener {
            val ingredientEditText = EditText(context).apply {
                hint = "Enter ingredient"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            ingredientsContainer.addView(ingredientEditText)
        }

        view.findViewById<View>(R.id.addInstructionButton).setOnClickListener {
            val instructionEditText = EditText(context).apply {
                hint = "Enter instruction"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            instructionsContainer.addView(instructionEditText)
        }

        view.findViewById<View>(R.id.saveRecipeButton).setOnClickListener {
            val recipeName = view.findViewById<EditText>(R.id.recipeName).text.toString()
            val recipeDescription = view.findViewById<EditText>(R.id.recipeDescription).text.toString()

            val ingredients = mutableListOf<String>()
            for (i in 0 until ingredientsContainer.childCount) {
                val ingredientEditText = ingredientsContainer.getChildAt(i) as EditText
                ingredients.add(ingredientEditText.text.toString())
            }

            val instructions = mutableListOf<String>()
            for (i in 0 until instructionsContainer.childCount) {
                val instructionEditText = instructionsContainer.getChildAt(i) as EditText
                instructions.add(instructionEditText.text.toString())
            }

            val newRecipe = RecipeModel(
                id = 0,
                name = recipeName,
                description = recipeDescription,
                ingredients = ingredients.map { IngredientModel(it) },
                instructions = instructions.mapIndexed { index, instruction ->
                    InstructionModel(instructionID = index + 1, displayText = instruction, position = index + 1)
                },
                components = emptyList(),
                country = "",
                isPublic = true,
                keywords = "",
                numServings = 4,
                nutrition = NutritionModel(
                    calories = 0,
                    protein = 0,
                    fat = 0,
                    carbohydrates = 0,
                    sugar = 0,
                    fiber = 0
                ),
                originalVideoUrl = "",
                thumbnailUrl = "",
                userEmail = "user@example.com"
            )

            addRecipeViewModel.saveRecipe(newRecipe)

            Toast.makeText(context, "Recipe saved successfully", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}

