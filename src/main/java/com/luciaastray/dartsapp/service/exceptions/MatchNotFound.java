package com.luciaastray.dartsapp.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchNotFound extends RuntimeException{
    public MatchNotFound() {
        super();
    }

    public MatchNotFound(String id) {
        super(String.format(ExceptionConstants.MATCH_NOT_FOUND, id));
    }

    public MatchNotFound(String id, Throwable cause) {
        super(String.format(ExceptionConstants.MATCH_NOT_FOUND, id), cause);
    }
}
