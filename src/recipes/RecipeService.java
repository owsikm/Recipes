package recipes;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    Recipe recipe;

    public void addRecipe(Recipe recipe){
this.recipe=recipe;
    }

    public Recipe getRecipe(){
        return recipe;
    }
}
