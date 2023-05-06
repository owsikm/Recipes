package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;


    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).get();
    }

    public void delete(long id) {
        recipeRepository.deleteById(id);
    }

    HashMap<Integer, Recipe> allRecipes = new HashMap<>();
}
