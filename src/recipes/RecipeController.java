package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable long id) {
        if (!recipeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @RequestMapping("/api/recipe/new")
    @PostMapping
    public ResponseEntity<Map<String, Object>> addRecipe(@RequestBody Recipe recipe) {
        if (recipe.getName()==null||recipe.getName().trim().length()==0
                ||recipe.getDescription()==null||recipe.getDescription().trim().length()==0
                ||recipe.getIngredients()==null||recipe.getIngredients().length==0
                ||recipe.getDirections()==null||recipe.getDirections().length==0){
            return ResponseEntity.badRequest().build();
        }
        recipeService.save(recipe);
        recipeService.allRecipes.put((int) recipe.getId(), recipe);
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", (int) recipe.getId());
        return ResponseEntity.ok(payload);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe (@PathVariable(value = "id") Long id)
    {
        if (!recipeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}