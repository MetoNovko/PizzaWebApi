package mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientDoesntExistException extends RuntimeException {
    public IngredientDoesntExistException() {
        super("The ingredient doesn't exist");
    }
}
