package recipes;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.catalina.filters.RestCsrfPreventionFilter;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class RecipeController {
    int counter = 0;
    RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable int id) {
        if (
                recipeService.allRecpies.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipeService.allRecpies.get(id), HttpStatus.OK);
    }

    @RequestMapping("/api/recipe/new")
    @PostMapping
    public ResponseEntity<Map<String, Object>> addRecipe(@RequestBody Recipe recipe) {
        counter++;
        recipeService.allRecpies.put(counter, recipe);
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", counter);
        return ResponseEntity.ok(payload);
    }

}