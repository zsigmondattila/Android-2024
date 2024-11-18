package com.example.recipehub.ui.recipe_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.recipehub.R
import com.example.recipehub.databinding.FragmentRecipeDetailBinding
import com.example.recipehub.domain.model.RecipeModel

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRecipeDetailBinding.bind(view)

        val recipeId = arguments?.getInt("recipeId") ?: return

        viewModel.getRecipeById(recipeId).observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                displayRecipeDetails(recipe)
            }
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
