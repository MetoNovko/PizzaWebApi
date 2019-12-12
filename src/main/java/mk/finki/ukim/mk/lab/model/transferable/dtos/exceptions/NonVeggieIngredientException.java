package mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NonVeggieIngredientException extends RuntimeException {
}
