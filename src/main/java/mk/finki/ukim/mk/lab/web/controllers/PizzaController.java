package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.transferable.dtos.SimplePizzaDTO;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPizza(@RequestBody SimplePizzaDTO pizza) {
        return this.pizzaService.createPizza(pizza);
    }

    @PutMapping("{id}")
    public Pizza editPizza(@PathVariable String id, @RequestBody SimplePizzaDTO pizza) {
        return this.pizzaService.editPizza(id, pizza);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deletePizza(@PathVariable String id) {
        this.pizzaService.deletePizza(id);
    }

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(required = false, defaultValue = "0") int totalIngredients) {
        return this.pizzaService.getAllPizzas(totalIngredients);
    }


    @GetMapping("/spicy:boolean")
    public List<Pizza> getPizzaWithSpicy(@RequestParam(name="spicy") boolean spicy) {
        return pizzaService.getPizzaWithSpicy();
    }

    @GetMapping("{id}")
    public Pizza getPizza(@PathVariable String id) {
        return this.pizzaService.getPizza(id);
    }

    @GetMapping("/compare")
    public List<Ingredient> getCommonIngredients(@RequestParam String pizza1, @RequestParam String pizza2) {
        return this.pizzaService.getCommonIngredients(pizza1, pizza2);
    }


}
