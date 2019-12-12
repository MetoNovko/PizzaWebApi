package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {

    @Query("select p from Pizza p where size(p.ingredients)>?1")
    List<Pizza> findByIngredientCount(int n);

    List<Pizza> findPizzaByIngredientsContains(Ingredient ingredient);

//    @Query("select p.name from Pizza p join p.ingredients i where p.ingredients=i.name and i.spicy=true")
//    List<Pizza> findBySpicyIsTrue(@Param(value = "spicy") boolean spicy);

    List<Pizza> findAllByIngredientsSpicyIsTrue();
}
