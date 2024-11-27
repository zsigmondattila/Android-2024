import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch

class AddRecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application)

    fun saveRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            recipeRepository.saveRecipeToDatabase(recipe)
        }
    }
}
