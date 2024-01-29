package org.meli.resistance.rescueapi.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InsufficientDataException extends RuntimeException {
    public InsufficientDataException(String message) {
        super(message);
    }

}
