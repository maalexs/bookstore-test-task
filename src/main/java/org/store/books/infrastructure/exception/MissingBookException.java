package org.store.books.infrastructure.exception;

public class MissingBookException extends RuntimeException {

    public MissingBookException(String message) {
        super(message);
    }
}
