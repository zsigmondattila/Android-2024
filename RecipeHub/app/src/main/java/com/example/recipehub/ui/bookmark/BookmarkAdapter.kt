package com.example.recipehub.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipehub.R
import com.example.recipehub.databinding.ItemBookmarkRecipeBinding
import com.example.recipehub.domain.model.RecipeModel

class BookmarkAdapter(
    private var recipes: List<RecipeModel>,
    private val onRecipeClickListener: OnRecipeClickListener,
    private val onUnbookmarkClickListener: OnUnbookmarkClickListener
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    // Frissíti a receptek listáját
    fun updateRecipes(newRecipes: List<RecipeModel>) {
        recipes = newRecipes
        notifyDataSetChanged()  // Egész lista frissítése
    }

    inner class BookmarkViewHolder(private val binding: ItemBookmarkRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.recipeTitle.text = recipe.name
            binding.recipeDescription.text = recipe.description
            Glide.with(binding.recipeImage.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.recipeImage)

            binding.root.setOnClickListener {
                onRecipeClickListener.onRecipeClick(recipe)
            }

            binding.unbookmarkButton.setOnClickListener {
                onUnbookmarkClickListener.onUnbookmarkClick(recipe)
            }
        }
    }

    interface OnRecipeClickListener {
        fun onRecipeClick(recipe: RecipeModel)
    }

    interface OnUnbookmarkClickListener {
        fun onUnbookmarkClick(recipe: RecipeModel)
    }
}
