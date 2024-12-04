package com.example.recipehub.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recipehub.R
import com.example.recipehub.databinding.FragmentHomeBinding
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.ui.recipes.RecipesViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            recipesViewModel.fetchRecipes()
        }

        recipesViewModel.recipes.observe(viewLifecycleOwner, Observer { recipeList ->
            if (recipeList.isNotEmpty()) {
                val randomRecipes = recipesViewModel.getRecipesOfTheWeek()
                val recommendations = recipesViewModel.getRecommendations()
                updateRecipesOfTheWeek(randomRecipes)
                updateRecommendations(recommendations)
            } else {
                Log.d("HomeFragment", "No recipes found.")
            }
        })
    }

    private fun updateRecipesOfTheWeek(randomRecipes: List<RecipeModel>) {
        val recipeOfTheWeekViews = listOf(
            binding.weekRecipe1, binding.weekRecipe2, binding.weekRecipe3,
            binding.weekRecipe4, binding.weekRecipe5
        )

        randomRecipes.forEachIndexed { index, recipe ->
            val imageView = recipeOfTheWeekViews[index]

            Glide.with(requireContext())
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)

            imageView.setContentDescription(recipe.name)
            imageView.setOnClickListener {
                navigateToRecipeDetails(recipe.id)
            }
        }
    }

    private fun updateRecommendations(recommendations: List<RecipeModel>) {
        val recommendationViews = listOf(
            binding.recommendation1, binding.recommendation2, binding.recommendation3,
            binding.recommendation4, binding.recommendation5
        )

        recommendations.forEachIndexed { index, recipe ->
            val imageView = recommendationViews[index]

            Glide.with(requireContext())
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)

            imageView.setContentDescription(recipe.name)
            imageView.setOnClickListener {
                navigateToRecipeDetails(recipe.id)
            }
        }
    }

    private fun navigateToRecipeDetails(recipeId: Int) {
        findNavController().navigate(
            R.id.action_navigation_home_to_navigation_recipe_detail,
            bundleOf("RECIPE_ID" to recipeId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}