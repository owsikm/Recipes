package recipes;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RecipeService {

    Recipe recipe;

    public void addRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void save() {

    }

    HashMap<Integer, Recipe> allRecpies = new HashMap<>();

}
