package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.transferable.dtos.SimplePizzaDTO;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.DuplicatePizzaNameException;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.IngredientDoesntExistException;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.NonVeggieIngredientException;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.PizzaDoesntExistException;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final IngredientService ingredientService;

    public PizzaServiceImpl(PizzaRepository pizzaRepo, IngredientService ingredientService) {
        this.pizzaRepository = pizzaRepo;
        this.ingredientService = ingredientService;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepository.findAll();
    }

    @Override
    public String createPizza(SimplePizzaDTO simplePizza) {
        Pizza pizza = getPizza(simplePizza);
        if (this.pizzaRepository.existsById(pizza.getName()))
            throw new DuplicatePizzaNameException();

        boolean isVeggie = pizza.getIngredients().stream().allMatch(Ingredient::isVeggie);

        if (pizza.isVeggie() && !isVeggie)
            throw new NonVeggieIngredientException();

        pizza.setVeggie(isVeggie);

        return this.pizzaRepository.save(pizza).getName();
    }

    private Pizza getPizza(SimplePizzaDTO simplePizza) {
        Pizza pizza = new Pizza();
        pizza.setName(simplePizza.getName());
        pizza.setDescription(simplePizza.getDescription());
        pizza.setIngredients(this.ingredientService.getIngredients(simplePizza.getIngredients()));
        pizza.setVeggie(simplePizza.isVeggie());

        return pizza;
    }

    @Override
    public Pizza editPizza(String id, SimplePizzaDTO simplePizza) {
        Pizza pizza = this.getPizza(simplePizza);
        if (!this.pizzaRepository.existsById(id))
            throw new PizzaDoesntExistException();
        return this.pizzaRepository.save(pizza);
    }

    @Override
    public void deletePizza(String id) {
        if (!this.pizzaRepository.existsById(id))
            throw new PizzaDoesntExistException();
        this.pizzaRepository.deleteById(id);
    }

    @Override
    public List<Pizza> getAllPizzas(int totalIngredients) {
        if (totalIngredients <= 0)
            return this.pizzaRepository.findAll();

        return this.pizzaRepository.findByIngredientCount(totalIngredients);
    }

    @Override
    public Pizza getPizza(String id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(PizzaDoesntExistException::new);
    }

    @Override
    public List<Ingredient> getCommonIngredients(String pizzaId1, String pizzaId2) {
        List<Ingredient> ingredients = this.getPizza(pizzaId1).getIngredients();
        return this.getPizza(pizzaId2).getIngredients()
                .stream()
                .filter(ingredients::contains)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pizza> getPizzaContaining(String ingredientId) {
        Ingredient ingredient = this.ingredientService.getIngredient(ingredientId)
                .orElseThrow(IngredientDoesntExistException::new);

        return this.pizzaRepository.findPizzaByIngredientsContains(ingredient);
    }

    @Override
    public List<Pizza> getPizzaWithSpicy() {

        return this.pizzaRepository.findAllByIngredientsSpicyIsTrue();
    }
}
