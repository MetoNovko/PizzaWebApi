package mk.finki.ukim.mk.lab.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String name;
    private boolean spicy;
    private float amount;
    private boolean veggie;

    public Ingredient(String name, boolean spicy, float amount, boolean veggie) {
        this.name = name;
        this.spicy = spicy;
        this.amount = amount;
        this.veggie = veggie;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Float.compare(that.getAmount(), getAmount()) == 0 &&
                isVeggie() == that.isVeggie() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(isSpicy(), that.isSpicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isSpicy(), getAmount(), isVeggie());
    }
}
