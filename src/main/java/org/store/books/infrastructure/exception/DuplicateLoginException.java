package org.store.books.infrastructure.exception;

public class DuplicateLoginException extends RuntimeException {

    public DuplicateLoginException(String message) {
        super(message);
    }
}
