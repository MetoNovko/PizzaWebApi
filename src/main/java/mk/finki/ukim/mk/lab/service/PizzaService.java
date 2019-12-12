package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.transferable.dtos.SimplePizzaDTO;

import java.util.List;

public interface PizzaService {
    List<Pizza> listPizzas();

    String createPizza(SimplePizzaDTO pizza);

    Pizza editPizza(String id, SimplePizzaDTO pizza);

    void deletePizza(String id);

    List<Pizza> getAllPizzas(int totalIngredients);

    Pizza getPizza(String id);

    List<Ingredient> getCommonIngredients(String pizzaId1, String pizzaId2);

    List<Pizza> getPizzaContaining(String ingredient);

    List<Pizza> getPizzaWithSpicy();
}
