package org.store.books.infrastructure.exception;

public class InvalidBookLinkException extends RuntimeException {

    public InvalidBookLinkException(String message) {
        super(message);
    }
}
