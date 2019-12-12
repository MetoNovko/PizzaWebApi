package mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicatePizzaNameException extends RuntimeException {

    public DuplicatePizzaNameException() {
        super("Pizza with the same name already exists!");
    }
}
