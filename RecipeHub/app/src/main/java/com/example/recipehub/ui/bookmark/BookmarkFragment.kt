package com.example.recipehub.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipehub.databinding.FragmentBookmarkBinding
import com.example.recipehub.ui.recipes.MyRecipesAdapter
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository

class BookmarkFragment : Fragment(), BookmarkAdapter.OnRecipeClickListener, BookmarkAdapter.OnUnbookmarkClickListener {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var bookmarkAdapter: BookmarkAdapter

    private val recipeRepository by lazy { RecipeRepository(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        // ViewModelProvider létrehozása a BookmarkFactory-val
        bookmarkViewModel = ViewModelProvider(this, BookmarkFactory(recipeRepository)).get(BookmarkViewModel::class.java)

        // RecyclerView beállítása
        setupRecyclerView()

        // Élő adat megfigyelése
        bookmarkViewModel.bookmarkedRecipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipes?.let {
                bookmarkAdapter.updateRecipes(it)
            }
        })

        // Könyvjelzőzött receptek betöltése
        bookmarkViewModel.loadBookmarkedRecipes()

        return binding.root
    }

    private fun setupRecyclerView() {
        // Adapter inicializálása és hozzárendelése a RecyclerView-hoz
        bookmarkAdapter = BookmarkAdapter(emptyList(), this, this)
        binding.myRecipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myRecipeRecyclerView.adapter = bookmarkAdapter
    }

    override fun onRecipeClick(recipe: RecipeModel) {
        // Recept kattintás kezelése
    }

    override fun onUnbookmarkClick(recipe: RecipeModel) {
        // Recept eltávolítás a könyvjelzőkből
        bookmarkViewModel.removeBookmark(recipe)
    }
}
