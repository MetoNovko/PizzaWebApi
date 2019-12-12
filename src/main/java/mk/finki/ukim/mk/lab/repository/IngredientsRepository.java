package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, String> {
    //@Query("select i from Ingredient where i.spicy=spicy")
    List<Ingredient> findAllBySpicyIsTrue();//@Param(value = "spicy") boolean spicy
    int countAllBySpicyIsTrue();
}
