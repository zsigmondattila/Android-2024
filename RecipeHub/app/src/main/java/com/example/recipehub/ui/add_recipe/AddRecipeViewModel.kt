import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipehub.domain.model.RecipeModel
import com.example.recipehub.repository.recipe.RecipeRepository
import kotlinx.coroutines.launch

class AddRecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    fun saveRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            repository.saveRecipeToDatabase(recipe)
        }
    }
}