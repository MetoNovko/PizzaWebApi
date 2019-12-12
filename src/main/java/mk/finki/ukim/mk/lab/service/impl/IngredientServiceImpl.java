package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.DuplicateIngredientNameException;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.IngredientDoesntExistException;
import mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions.SpicyIngredientAmountExceed;
import mk.finki.ukim.mk.lab.repository.IngredientsRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public String createIngredient(Ingredient ingredient) {
        if (ingredient.isSpicy() && this.ingredientsRepository.countAllBySpicyIsTrue() > 2)
            throw new SpicyIngredientAmountExceed();
        if (this.ingredientsRepository.existsById(ingredient.getName()))
            throw new DuplicateIngredientNameException();
        return this.ingredientsRepository.save(ingredient).getName();
    }

    @Override
    public Ingredient editIngredient(String id, Ingredient ingredient) {
        ingredient.setName(id);
        return this.ingredientsRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String id) {
        if (!this.ingredientsRepository.existsById(id)) {
            throw new IngredientDoesntExistException();
        }

        this.ingredientsRepository.deleteById(id);
    }

    @Override
    public Optional<Ingredient> getIngredient(String id) {
        return this.ingredientsRepository.findById(id);
    }

    @Override
    public Page<Ingredient> getIngredients(int page, int size, boolean isSpicy) {
        if (isSpicy)
            return new PageImpl<>(this.ingredientsRepository.findAllBySpicyIsTrue());
        return this.ingredientsRepository.findAll(PageRequest.of(page, size, Sort.by("name").ascending()));
    }

    @Override
    public List<Ingredient> getIngredients(List<String> ingredientIds) {
        List<Ingredient> ingredients = this.ingredientsRepository.findAllById(ingredientIds);
        if (ingredientIds.size() != ingredients.size()) {
            throw new IngredientDoesntExistException();
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> getSpicyIngredients(boolean isSpicy) {
        if (isSpicy)
            return this.ingredientsRepository.findAllBySpicyIsTrue();
        return new ArrayList<>();
    }
}
