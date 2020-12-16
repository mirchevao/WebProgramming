package web.programming.aud.wpaud.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTeacherFoundException extends RuntimeException {
    public NoTeacherFoundException(Long id) {
        super(String.format("Product with id: %d was not found", id));
    }

}
