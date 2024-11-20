package com.example.recipehub.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipehub.R
import com.example.recipehub.databinding.ItemRecipeBinding
import com.example.recipehub.domain.model.RecipeModel

class MyRecipesAdapter(
    private var recipes: List<RecipeModel>,
    private val onRecipeClickListener: OnRecipeClickListener
) : RecyclerView.Adapter<MyRecipesAdapter.RecipeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<RecipeModel>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    inner class RecipeItemViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.recipeTitle.text = recipe.name
            binding.recipeDescription.text = recipe.description
            binding.recipeKeywords.text = recipe.keywords
            Glide.with(binding.recipeImage.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.recipeImage)

            binding.root.setOnClickListener {
                onRecipeClickListener.onRecipeClick(recipe)
            }
        }
    }

    interface OnRecipeClickListener {
        fun onRecipeClick(recipe: RecipeModel)
    }
}
