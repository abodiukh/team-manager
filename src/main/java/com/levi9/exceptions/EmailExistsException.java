package com.levi9.exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(final String message) {
        super(message);
    }
}
