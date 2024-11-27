package com.example.recipehub.ui.my_recipes

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipehub.R
import com.example.recipehub.databinding.FragmentRecipesBinding
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.ui.recipes.RecipesViewModel
import com.example.recipehub.ui.recipes.RecipesAdapter
import kotlinx.coroutines.launch

class MyRecipesFragment : Fragment() {


    private val recipeListViewModel: RecipesViewModel by viewModels()
    private lateinit var recipeAdapter: RecipesAdapter
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        lifecycleScope.launch {
            recipeListViewModel.fetchRecipes()

            recipeListViewModel.recipes.observe(viewLifecycleOwner, Observer { recipeList ->
                if (recipeList.isNotEmpty()) {
                    recipeAdapter.updateRecipes(recipeList)
                } else {
                    Log.d("RecipesFragment", "No recipes found.")
                }
            })
        }
    }


    private fun initRecyclerView() {
        recipeAdapter = RecipesAdapter(emptyList(), object : RecipesAdapter.OnRecipeClickListener {
            override fun onRecipeClick(recipe: RecipeModel) {
                navigateToRecipeDetails(recipe)
            }
        })

        binding.recipeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }
    }

    private fun navigateToRecipeDetails(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_navigation_my_recipes_to_navigation_recipe_detail,
            bundleOf("recipeId" to recipe.id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}