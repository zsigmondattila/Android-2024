package com.example.recipehub.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
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
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.recipehub.utils.SharedPreferences


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

        val sharedPreferences = SharedPreferences(requireContext())
        val givenName = sharedPreferences.getGivenName()
        val avatar = sharedPreferences.getPicture()

        if (!TextUtils.isEmpty(givenName)) {
            binding.helloUser.text = "Welcome, $givenName!"
        }

        if (!TextUtils.isEmpty(avatar)) {
            Glide.with(requireContext())
                .load(avatar)
                .placeholder(R.drawable.placeholder_image)
                .circleCrop()
                .into(binding.avatarImage)
        }

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

    private fun updateRecipesOfTheWeek(recipesOfTheWeek: List<RecipeModel>) {
        val container = binding.recipesOfTheWeekContainer
        container.removeAllViews()

        recipesOfTheWeek.forEach { recipe ->
            val itemLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginEnd = 16.dp
                }
                gravity = Gravity.CENTER
            }

            val imageView = ImageView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(220.dp, 160.dp)
                contentDescription = recipe.name

                setBackgroundResource(R.drawable.rounded)
                clipToOutline = true
                outlineProvider = ViewOutlineProvider.BACKGROUND
            }

            Glide.with(requireContext())
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .transform(RoundedCorners(36))
                .centerCrop()
                .into(imageView)

            val textView = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 8.dp
                }

                val displayText = if (recipe.name.length > 15) {
                    recipe.name.take(15) + "..."
                } else {
                    recipe.name
                }

                text = displayText
                textSize = 14f
                gravity = Gravity.CENTER
            }

            imageView.setOnClickListener {
                navigateToRecipeDetails(recipe.id)
            }

            itemLayout.addView(imageView)
            itemLayout.addView(textView)
            container.addView(itemLayout)
        }
    }


    private fun updateRecommendations(recommendations: List<RecipeModel>) {
        val container = binding.recommendationsContainer
        container.removeAllViews()

        recommendations.forEach { recipe ->
            val itemLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginEnd = 16.dp
                }
                gravity = Gravity.CENTER
            }

            val imageView = ImageView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(140.dp, 190.dp)
                contentDescription = recipe.name

                setBackgroundResource(R.drawable.rounded)
                clipToOutline = true
                outlineProvider = ViewOutlineProvider.BACKGROUND
            }

            Glide.with(requireContext())
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .transform(RoundedCorners(36))
                .centerCrop()
                .into(imageView)

            val textView = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 8.dp
                }

                val displayText = if (recipe.name.length > 15) {
                    recipe.name.take(15) + "..."
                } else {
                    recipe.name
                }

                text = displayText
                textSize = 14f
                gravity = Gravity.CENTER
            }

            imageView.setOnClickListener {
                navigateToRecipeDetails(recipe.id)
            }

            itemLayout.addView(imageView)
            itemLayout.addView(textView)
            container.addView(itemLayout)
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

    val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()

}