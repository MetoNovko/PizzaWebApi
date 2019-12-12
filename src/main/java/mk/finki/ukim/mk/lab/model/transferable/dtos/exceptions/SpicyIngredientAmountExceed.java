package mk.finki.ukim.mk.lab.model.transferable.dtos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SpicyIngredientAmountExceed extends RuntimeException {
    public SpicyIngredientAmountExceed() {
        super("Maximum number of spicy ingredients exceeded.");
    }
}
