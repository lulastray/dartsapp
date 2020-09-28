package com.luciaastray.dartsapp.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidUUID extends RuntimeException {
    public NotValidUUID() {
        super();
    }

    public NotValidUUID(String id) {
        super(String.format(ExceptionConstants.NOT_VALID_UUID, id));
    }

    public NotValidUUID(String id, Throwable cause) {
        super(String.format(ExceptionConstants.NOT_VALID_UUID, id), cause);
    }
}
