package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.IngredientDoesntExistException;
import mk.finki.ukim.mk.lab.service.IngredientService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    private final IngredientService ingredientService;
    private final PizzaService pizzaService;

    public IngredientsController(IngredientService ingredientService, PizzaService pizzaService) {
        this.ingredientService = ingredientService;
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.createIngredient(ingredient);
    }

    @PatchMapping("{id}")
    public Ingredient editIngredient(@PathVariable String id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.editIngredient(id, ingredient);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteIngredient(@PathVariable String id) {
        this.ingredientService.deleteIngredient(id);
    }

    @GetMapping("{id}")
    public Ingredient getIngredient(@PathVariable String id) {
        return this.ingredientService.getIngredient(id)
                .orElseThrow(IngredientDoesntExistException::new);
    }

    @GetMapping
    public Page<Ingredient> getIngredients(@RequestParam(required = false, defaultValue = "10") int size,
                                           @RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "false") boolean isSpicy) {
        return this.ingredientService.getIngredients(page, size, isSpicy);
    }

    @GetMapping("/{ingredient}/pizzas")
    public List<Pizza> getPizzasContaining(@PathVariable String ingredient) {
        return this.pizzaService.getPizzaContaining(ingredient);
    }


}
