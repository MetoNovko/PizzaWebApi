package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    String createIngredient(Ingredient ingredient);

    Ingredient editIngredient(String id, Ingredient ingredient);

    void deleteIngredient(String id);

    Optional<Ingredient> getIngredient(String id);

    Page<Ingredient> getIngredients(int page, int size, boolean isSpicy);

    List<Ingredient> getIngredients(List<String> ingredientIds);

    List<Ingredient> getSpicyIngredients(boolean isSpicy);
}
